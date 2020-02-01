<template>
  <div>
    <el-table
      :data="listClients.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :default-sort="{prop: 'id', order: 'ascending'}">
      <el-table-column prop="id" label="id" sortable width="300"></el-table-column>
      <el-table-column prop="name" label="name" sortable width="180"></el-table-column>
      <el-table-column prop="description" label="description" sortable width="180"></el-table-column>
      <el-table-column align="left" width="180">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="search"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleForm(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteClient(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="handleForm(null,null)">Add Client</el-button>
  <el-dialog
    :title="formName"
    :visible.sync="formVisible"
    :before-close="hideForm">
    <el-form ref="form" :model="formData" label-width="100px">
      <el-form-item label="Id" disabled="true" hidden>
        <el-input v-model="formData.id"/>
      </el-form-item>
      <el-form-item label="Name">
        <el-input v-model="formData.name"/>
      </el-form-item>
      <el-form-item label="Description">
        <el-input v-model="formData.description"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="confirmForm(formName, formData.id,formData.name,formData.description)" >OK</el-button>
        <el-button @click="hideForm">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  </div>
</template>

<script>
import {addObject, deleteObject, listObject, updateObject} from './method'
const formJson = {
  id: '',
  name: '',
  description: ''
}
export default {
  name: 'Clients',
  data () {
    return {
      listClients: [],
      search: '',
      formVisible: false,
      formData: formJson,
      formName: null
    }
  },
  mounted () {
    this.listClient()
  },
  methods: {
    listClient () {
      listObject('client').then(response => {
        this.listClients = response.data
      })
    },
    deleteClient (index, row) {
      deleteObject('client', {id: row.id}).then(response => {
        this.listClient()
      })
    },
    updateClient (id, name, description) {
      updateObject('client', {id: id, name: name, description: description}).then(response => {
        this.formVisible = false
        this.listClient()
      })
    },
    addClient (name, description) {
      addObject('client', {name: name, description: description}).then(response => {
        this.formVisible = false
        this.listClient()
      })
    },
    hideForm () {
      // 更改值
      this.formVisible = !this.formVisible
      return true
    },
    handleForm (index, row) {
      this.formVisible = true
      this.formData = JSON.parse(JSON.stringify(formJson))
      if (row !== null) {
        this.formData = Object.assign({}, row)
      }
      this.formName = 'add'
      if (index !== null) {
        this.index = index
        this.formName = 'update'
      }
    },
    confirmForm (formName, id, name, description) {
      if (formName === 'update') {
        this.updateClient(id, name, description)
      } else {
        this.addClient(name, description)
      }
    }
  }
}
</script>

<style>
</style>
