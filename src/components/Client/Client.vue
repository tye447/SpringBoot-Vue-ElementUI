<template>
  <div>
    <el-table
      :data="listClients.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column prop="id" label="id" width="300"></el-table-column>
      <el-table-column prop="name" label="name" width="180"></el-table-column>
      <el-table-column prop="age" label="age" width="180"></el-table-column>
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
            @click="deleteClient(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="addPage">Add Client</el-button>
  <el-dialog
    :title="formName"
    :visible.sync="formVisible"
    :before-close="hideForm">
    <el-form ref="form" :model="formData" label-width="80px">
      <el-form-item label="Id">
        <el-input v-model="formData.id" :readonly="true"/>
      </el-form-item>
      <el-form-item label="Name">
        <el-input v-model="formData.name"/>
      </el-form-item>
      <el-form-item label="Age">
        <el-input v-model="formData.age"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateClient(formData.id,formData.name,formData.age)">修改</el-button>
        <el-button @click="hideForm">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import {deleteObject, listObject, updateObject} from '../method'
const formJson = {
  id: '',
  name: '',
  age: ''
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
    addPage () {
      this.$router.push('add')
    },
    modifyPage (index, row) {
      this.$router.push('update')
    },
    handleEdit (index, row) {
      console.log(index, row)
    },
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
    updateClient (id, name, age) {
      updateObject('client', {id: id, name: name, age: age}).then(response => {
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
        console.log('23333')
        this.formData = Object.assign({}, row)
      }
      this.formName = 'add'
      if (index !== null) {
        this.index = index
        this.formName = 'edit'
      }
    }
  }
}
</script>

<style>
</style>
