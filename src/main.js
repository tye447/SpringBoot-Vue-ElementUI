// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Axios from 'axios'
Axios.defaults.baseURL = 'http://localhost:8008/'
Vue.config.productionTip = false
Vue.prototype.$axios = Axios
Axios.defaults.headers.post['Content-Type'] = 'application/json'
Vue.use(ElementUI)

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
