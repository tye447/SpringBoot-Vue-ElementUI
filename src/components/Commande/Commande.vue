<template>
  <div>
    <el-button @click="addPage">Test</el-button>
    <el-table
      :data="listCommandes.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column prop="id" label="id" width="300"></el-table-column>
      <el-table-column prop="client.name" label="client_name" width="180"></el-table-column>
      <el-table-column prop="product.name" label="product_name" width="180"></el-table-column>
      <el-table-column prop="employee.name" label="employee_name" width="180"></el-table-column>
      <el-table-column prop="quantity" label="quantity" width="180"></el-table-column>
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
  name: 'Commande',
  data () {
    return {
      form: {id: ''},
      activeIndex: '1',
      listCommandes: [],
      tableData: [],
      search: ''
    }
  },
  mounted () {
    this.listCommande()
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
      Axios.delete('commande/delete', {
        params: {
          id: row.id
        }
      }).then(response => {
        if (response.status === 200) {
          console.log('删除成功')
        } else {
          console.log('删除失败')
        }
        this.listCommande()
      }).catch(function (error) {
        console.log(error)
      })
    },
    listCommande: function () {
      Axios.get('commande/list')
        .then(response => {
          this.listCommandes = response.data
          console.log('list commandes OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    handleClick (row) {
      console.log(row.id)
    },
    getCommande: function () {
      Axios.get('commande/get', {
        params: {
          id: this.form.id
        }
      }).then(response => {
        console.log('get commande OK')
        let aCommande = []
        aCommande.push(response.data)
        this.tableData = aCommande
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
