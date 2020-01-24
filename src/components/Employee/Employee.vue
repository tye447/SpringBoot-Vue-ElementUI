<template>
  <div>
    <el-button @click="addPage">Test</el-button>
    <el-table
      :data="listEmployees.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column prop="id" label="id" width="300"></el-table-column>
      <el-table-column prop="name" label="name" width="180"></el-table-column>
      <el-table-column prop="age" label="age" width="180"></el-table-column>
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
            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  name: 'Employee',
  data () {
    return {
      form: {id: ''},
      activeIndex: '1',
      listEmployees: [],
      tableData: [],
      search: ''
    }
  },
  mounted () {
    this.listEmployee()
  },
  methods: {
    addPage () {
      this.$router.push('add')
    },
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(row.id)
      Axios.delete('employee/delete', {
        params: {
          id: row.id
        }
      }).then(response => {
        if (response.status === 200) {
          console.log('删除成功')
        } else {
          console.log('删除失败')
        }
        this.listEmployee()
      }).catch(function (error) {
        console.log(error)
      })
    },
    listEmployee: function () {
      Axios.get('employee/list')
        .then(response => {
          this.listEmployees = response.data
          console.log('employees OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    handleClick (row) {
      console.log(row.id)
    },
    getEmployee: function () {
      Axios.get('employee/get', {
        params: {
          id: this.form.id
        }
      }).then(response => {
        console.log('get employee OK')
        let aEmployee = []
        aEmployee.push(response.data)
        this.tableData = aEmployee
      })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>

<style>
</style>
