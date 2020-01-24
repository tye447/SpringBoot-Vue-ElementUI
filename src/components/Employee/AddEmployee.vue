<template>
  <div>
    <el-button @click="returnPage">Return</el-button>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="form.employeeName"/>
      </el-form-item>
      <el-form-item label="Age">
        <el-input v-model="form.employeeAge"/>
      </el-form-item>
      <el-form-item label="Password">
        <el-input v-model="form.employeePassword"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addEmployee">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'AddEmployee',
  data () {
    return {
      form: {
        employeeName: '',
        employeeAge: '',
        employeePassword: ''
      }
    }
  },
  methods: {
    returnPage () {
      this.$router.push('list')
    },
    addEmployee: function () {
      this.$axios.post('employee/add',
        'name=' + this.form.employeeName + '&age=' + this.form.employeeAge + '&password=' + this.form.employeePassword
      ).then(response => {
        console.log('get employee OK')
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
