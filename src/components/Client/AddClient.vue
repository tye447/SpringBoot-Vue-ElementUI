<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="form.clientName"/>
      </el-form-item>
      <el-form-item label="Age">
        <el-input v-model="form.clientAge"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addClient">立即创建</el-button>
        <el-button @click="returnPage">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {addObject} from '../method'

export default {
  name: 'AddClient',
  data () {
    return {
      form: {
        clientName: '',
        clientAge: ''
      }
    }
  },
  methods: {
    returnPage () {
      this.$router.push('list')
    },
    addClient: function () {
      addObject('client', {name: this.form.clientName, age: this.form.clientAge}).then(response => {
        this.$router.push('list')
      })
    }
  }
}
</script>

<style scoped>
</style>
