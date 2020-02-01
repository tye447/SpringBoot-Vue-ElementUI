<template>
  <div>
    <el-table
      :data="listCommandes.filter(data => !search || data.client.name.toLowerCase().includes(search.toLowerCase())
      || data.product.name.toLowerCase().includes(search.toLowerCase())|| data.employee.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :default-sort="{prop: 'id', order: 'ascending'}">
      <el-table-column prop="id" label="id" sortable width="80"></el-table-column>
      <el-table-column prop="client.name" label="client_name" sortable width="180"></el-table-column>
      <el-table-column prop="product.name" label="product_name" sortable width="180"></el-table-column>
      <el-table-column prop="employee.name" label="employee_name" sortable width="180"></el-table-column>
      <el-table-column prop="quantity" label="quantity" sortable width="100"></el-table-column>
      <el-table-column prop="state" label="state" sortable width="180"></el-table-column>
      <el-table-column align="left" width="280">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="search"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini" :disabled="scope.row.state=='confirmed'?true:false"
            @click="confirm(scope.$index, scope.row)">Confirm</el-button>
          <el-button
            size="mini" :disabled="scope.row.state=='confirmed'?true:false"
            @click="handleForm(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger" :disabled="scope.row.state=='confirmed'?true:false"
            @click="deleteCommande(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="handleForm(null,null)">Add Commande</el-button>
    <el-dialog
      :title="formName"
      :visible.sync="formVisible"
      :before-close="hideForm">
      <el-form ref="form" :model="formData" label-width="100px">
        <el-form-item label="Id" disabled="true" hidden>
          <el-input v-model="formData.id"/>
        </el-form-item>
        <el-select v-model="formData.client.name" placeholder="Please choose">
          <el-option
            v-for="item in formData.listClients"
            :key="item.id"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
        <el-select v-model="formData.employee.name" placeholder="Please choose">
          <el-option
            v-for="item in formData.listEmployees"
            :key="item.id"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
        <el-select v-model="formData.product.name" placeholder="Please choose">
          <el-option
            v-for="item in formData.listProducts"
            :key="item.id"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
        <el-form-item label="quantity">
          <el-input v-model="formData.quantity"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="confirmForm(formName, formData.id,formData.client.name,formData.employee.name,formData.product.name,formData.quantity)">OK</el-button>
          <el-button @click="hideForm">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {addObject, deleteObject, listObject, updateObject, confirmCommande} from './method'
const formJson = {
  id: '',
  client: [],
  employee: [],
  product: [],
  quantity: '',
  state: 'no_confirm',
  listClients: [],
  listEmployees: [],
  listProducts: []
}
export default {
  name: 'Commande',
  data () {
    return {
      listCommandes: [],
      search: '',
      formVisible: false,
      formData: formJson,
      formName: null
    }
  },
  mounted () {
    this.listCommande()
    this.listClient()
    this.listEmployee()
    this.listProduct()
  },
  methods: {
    listClient () {
      listObject('client').then(response => {
        this.formData.listClients = response.data
      })
    },
    listEmployee () {
      listObject('employee').then(response => {
        this.formData.listEmployees = response.data
      })
    },
    listProduct () {
      listObject('product').then(response => {
        this.formData.listProducts = response.data
      })
    },
    listCommande () {
      listObject('commande').then(response => {
        this.listCommandes = response.data
      })
    },
    deleteCommande (index, row) {
      deleteObject('commande', {id: row.id}).then(response => {
        this.listCommande()
      })
    },
    updateCommande (id, cName, eName, pName, quantity) {
      updateObject('commande', {id: id, client_name: cName, employee_name: eName, product_name: pName, quantity: quantity}).then(response => {
        this.formVisible = false
        this.listCommande()
      })
    },
    addCommande (cName, eName, pName, quantity) {
      addObject('commande', {
        client_name: cName,
        employee_name: eName,
        product_name: pName,
        quantity: quantity}).then(response => {
        this.formVisible = false
        this.listCommande()
      })
    },
    hideForm () {
      this.formVisible = !this.formVisible
      return true
    },
    handleForm (index, row) {
      this.formVisible = true
      this.formName = 'add'
      if (row !== null) {
        this.formData = Object.assign({}, row)
      }
      if (index !== null) {
        this.formData = JSON.parse(JSON.stringify(formJson))
        this.formData.id = row.id
        this.formData.client = row.client
        this.formData.employee = row.employee
        this.formData.product = row.product
        this.formData.quantity = row.quantity
        this.formData.state = row.state
        this.index = index
        this.formName = 'update'
        this.listClient()
        this.listEmployee()
        this.listProduct()
      }
    },
    confirm (index, row) {
      confirmCommande({id: row.id}).then(response => {
        this.listCommande()
      }
      )
    },
    confirmForm (formName, id, cName, eName, pName, quantity) {
      if (formName === 'update') {
        this.updateCommande(id, cName, eName, pName, quantity)
      } else {
        this.addCommande(cName, eName, pName, quantity)
      }
    }
  }
}
</script>

<style>
</style>
