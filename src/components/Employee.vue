<template>
  <div>
    <el-table
      :data="listEmployees.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :default-sort="{prop: 'id', order: 'ascending'}">
      <el-table-column prop="id" label="id" sortable width="300"></el-table-column>
      <el-table-column prop="name" label="name" sortable width="180"></el-table-column>
      <el-table-column prop="age" label="age" sortable width="180"></el-table-column>
      <el-table-column align="left" width="180">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索名字"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleForm(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteEmployee(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="handleForm(null,null)">Add Employee</el-button>
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
        <el-form-item label="Age">
          <el-input v-model="formData.age"/>
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="formData.password"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="confirm(formName, formData.id,formData.name,formData.age,formData.password)" >确定</el-button>
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
  age: '',
  password: ''
}
export default {
  name: 'Employee',
  data () {
    return {
      listEmployees: [],
      search: '',
      formVisible: false,
      formData: formJson,
      formName: null
    }
  },
  mounted () {
    this.listEmployee()
  },
  methods: {
    listEmployee () {
      listObject('employee').then(response => {
        this.listEmployees = response.data
      })
    },
    deleteEmployee (index, row) {
      deleteObject('employee', {id: row.id}).then(response => {
        this.listEmployee()
      })
    },
    updateEmployee (id, name, age) {
      updateObject('employee', {id: id, name: name, age: age}).then(response => {
        this.formVisible = false
        this.listEmployee()
      })
    },
    addEmployee (name, age, password) {
      addObject('employee', { name: name, age: age, password: password }).then(response => {
        this.formVisible = false
        this.listEmployee()
      })
    },
    hideForm () {
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
    confirm (formName, id, name, age, password) {
      if (formName === 'update') {
        this.updateEmployee(id, name, age, password)
      } else {
        this.addEmployee(name, age, password)
      }
    }
  }
}
</script>

<style>
</style>
