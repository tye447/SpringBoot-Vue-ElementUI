<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">Welcome to Log In!</h3>
      <el-form-item label="username" prop="username">
        <el-input type="text" placeholder="Please enter the username" v-model="form.username"/>
      </el-form-item>
      <el-form-item label="password" prop="password">
        <el-input type="password" placeholder="Please enter the password" v-model="form.password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="onSubmit('loginForm')">Log In</el-button>
      </el-form-item>
    </el-form>

    <el-dialog
      title="Notification"
      :visible.sync="dialogVisible"
      width="30%">
      <span>Please enter the username and password!</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">OK</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {login} from './method'
export default {
  name: 'Login',
  data () {
    return {
      form: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: 'Username cannot be empty', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'Password cannot be empty', trigger: 'blur'}
        ]
      },
      dialogVisible: false
    }
  },
  methods: {
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login({name: this.form.username, password: this.form.password})
        } else {
          this.dialogVisible = true
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
