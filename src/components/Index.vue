<template>
  <div>
    <el-container>
      <div align="right">
        <el-button type="danger" @click="logout" width="100px">Logout</el-button>
      </div>
      <el-header>Supermarket Admin System</el-header>

      <el-container>
        <el-aside width="200px">
          <el-menu router
                   default-active="/client/list"
                   class="el-menu-vertical-demo"
                   background-color="#545c64"
                   text-color="#fff"
                   active-text-color="#ffd04b">
            <el-menu-item index="/employee/list">
              <i class="el-icon-document"></i>
              <span slot="title">Employees</span>
            </el-menu-item>
            <el-menu-item index="/client/list">
              <i class="el-icon-document"></i>
              <span slot="title">Clients</span>
            </el-menu-item>
            <el-menu-item index="/product/list">
              <i class="el-icon-document"></i>
              <span slot="title">Products</span>
            </el-menu-item>
            <el-menu-item index="/commande/list">
              <i class="el-icon-document"></i>
              <span slot="title">Commandes</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      msg: 'test'
    }
  },
  methods: {
    logout () {
      this.$router.push('/login')
    }
  }
}
</script>

<style>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
