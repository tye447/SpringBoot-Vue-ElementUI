<template>
  <div>
    <el-button @click="returnPage">Return</el-button>
    <el-form ref="form" :model="form" label-width="80px">
      <el-select v-model="form.listClients.name" placeholder="请选择">
        <el-option
          v-for="item in form.listClients"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-select v-model="form.listEmployees.name" placeholder="请选择">
        <el-option
          v-for="item in form.listEmployees"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-select v-model="form.listProducts.name" placeholder="请选择">
        <el-option
          v-for="item in form.listProducts"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-form-item label="quantity">
        <el-input v-model="form.quantity"/>
      </el-form-item>
      <el-button type="primary" @click="addCommande">立即创建</el-button>
      <el-button>取消</el-button>
    </el-form>
  </div>
</template>

<script>
import Axios from 'axios'

export default {
  name: 'AddCommande',
  data () {
    return {
      form: {
        listClients: [],
        listEmployees: [],
        listProducts: [],
        quantity: ''
      }
    }
  },
  mounted () {
    this.listClient()
    this.listEmployee()
    this.listProduct()
  },
  methods: {
    listClient: function () {
      Axios.get('client/list')
        .then(response => {
          this.form.listClients = response.data
          console.log('list client OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    listEmployee: function () {
      Axios.get('employee/list')
        .then(response => {
          this.form.listEmployees = response.data
          console.log('list e OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    listProduct: function () {
      Axios.get('product/list')
        .then(response => {
          this.form.listProducts = response.data
          console.log('list p OK')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    returnPage () {
      this.$router.push('list')
    },
    addCommande: function () {
      this.$axios.post('commande/add',
        'client_name=' + this.form.listClients.name +
        '&employee_name=' + this.form.listEmployees.name +
        '&product_name=' + this.form.listProducts.name +
        '&quantity=' + this.form.quantity
      ).then(response => {
        console.log('get commande OK')
        this.$router.push('list')
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
</style>
