<template>
  <div>
    <el-button @click="returnPage">Return</el-button>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="form.productName"/>
      </el-form-item>
      <el-form-item label="Price">
        <el-input v-model="form.productPrice"/>
      </el-form-item>
      <el-form-item label="Stock">
        <el-input v-model="form.productStock"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addProduct">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {addObject} from '../method'

export default {
  name: 'AddProduct',
  data () {
    return {
      form: {
        productName: '',
        productPrice: '',
        productStock: ''
      }
    }
  },
  methods: {
    returnPage () {
      this.$router.push('list')
    },
    addProduct: function () {
      addObject('product', {
        'name': this.form.productName,
        'price': this.form.productPrice,
        'stock': this.form.productStock
      }).then(response => {
        console.log('get product OK')
        this.$router.push('list')
      })
    }
  }
}
</script>

<style scoped>
</style>
