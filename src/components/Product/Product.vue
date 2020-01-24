<template>
  <div>
    <el-button @click="addPage">Test</el-button>
    <el-table
      :data="listProducts.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column prop="id" label="id" width="300"></el-table-column>
      <el-table-column prop="name" label="name" width="180"></el-table-column>
      <el-table-column prop="price" label="price" width="180"></el-table-column>
      <el-table-column prop="stock" label="stock" width="180"></el-table-column>
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
  name: 'Product',
  data () {
    return {
      form: {id: ''},
      activeIndex: '1',
      listProducts: [],
      tableData: [],
      search: ''
    }
  },
  mounted () {
    this.listProduct()
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
      Axios.delete('product/delete', {
        params: {
          id: row.id
        }
      }).then(response => {
        if (response.status === 200) {
          console.log('删除成功')
        } else {
          console.log('删除失败')
        }
        this.listProduct()
      }).catch(function (error) {
        console.log(error)
      })
    },
    listProduct: function () {
      Axios.get('product/list')
        .then(response => {
          this.listProducts = response.data
          console.log('products OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    handleClick (row) {
      console.log(row.id)
    },
    getProduct: function () {
      Axios.get('product/get', {
        params: {
          id: this.form.id
        }
      }).then(response => {
        console.log('get product OK')
        let aProduct = []
        aProduct.push(response.data)
        this.tableData = aProduct
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
