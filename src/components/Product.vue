<template>
  <div>
    <el-table
      :data="listProducts.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :default-sort="{prop: 'id', order: 'ascending'}">
      <el-table-column prop="id" label="id" sortable width="300"></el-table-column>
      <el-table-column prop="name" label="name" sortable width="180"></el-table-column>
      <el-table-column prop="price" label="price" sortable width="180"></el-table-column>
      <el-table-column prop="stock" label="stock" sortable width="180"></el-table-column>
      <el-table-column align="left" width="180">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleForm(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteProduct(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="handleForm(null,null)">Add Product</el-button>
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
        <el-form-item label="Price">
          <el-input v-model="formData.price"/>
        </el-form-item>
        <el-form-item label="Stock">
          <el-input v-model="formData.stock"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="confirmForm(formName, formData.id,formData.name,formData.price,formData.stock)" >确定</el-button>
          <el-button @click="hideForm">取消</el-button>
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
  price: '',
  stock: ''
}
export default {
  name: 'Product',
  data () {
    return {
      listProducts: [],
      search: '',
      formVisible: false,
      formData: formJson,
      formName: null
    }
  },
  mounted () {
    this.listProduct()
  },
  methods: {
    listProduct () {
      listObject('product').then(response => {
        this.listProducts = response.data
      })
    },
    deleteProduct (index, row) {
      deleteObject('product', {id: row.id}).then(response => {
        this.listProduct()
      })
    },
    updateProduct (id, name, price, stock) {
      updateObject('product', {id: id, name: name, price: price, stock: stock}).then(response => {
        this.formVisible = false
        this.listProduct()
      })
    },
    addProduct (name, price, stock) {
      addObject('product', {name: name, price: price, stock: stock}).then(response => {
        this.formVisible = false
        this.listProduct()
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
    confirmForm (formName, id, name, price, stock) {
      if (formName === 'update') {
        this.updateProduct(id, name, price, stock)
      } else {
        this.addProduct(name, price, stock)
      }
    }
  }
}
</script>

<style>
</style>
