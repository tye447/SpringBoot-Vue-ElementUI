<template>
  <div>
    <el-button @click="returnPage">Return</el-button>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="form.clientName"/>
      </el-form-item>
      <el-form-item label="Age">
        <el-input v-model="form.clientAge"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="UpdateClient">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {updateObject} from '../method'
export default {
  name: 'UpdateClient',
  methods: {
    returnPage () {
      this.$router.push('list')
    },
    updateClient (index, row) {
      console.log(row.name)
      updateObject('client', {id: row.id, name: row.name, age: row.age}).then(response => {
        this.listClient()
      })
    }
  }
}

</script>

<style scoped>

</style>
