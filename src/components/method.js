import Axios from 'axios'
import router from '../router'
function listObject (table) {
  return Axios.get(table + '/list')
    .then(response => {
      // console.log('list')
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function login (params) {
  Axios.post('/login', {}, {params})
    .then(response => {
      console.log(response)
      if (response.data !== '') {
        router.push('/client/list')
        sessionStorage.setItem('username', response.data)
      }
    }).catch(function (error) {
      alert('user not found!')
      console.log(error)
    })
}
function logout () {
  sessionStorage.removeItem('username')
  router.push('/login')
}
function deleteObject (table, params) {
  return Axios.delete(table + '/delete', {params})
    .then(response => {
      // console.log('delete')
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function updateObject (table, params) {
  return Axios.post(table + '/update', {}, {params})
    .then(response => {
      // console.log('update')
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function addObject (table, params) {
  return Axios.post(table + '/add', {}, {params})
    .then(response => {
      // console.log('add')
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function confirmCommande (params) {
  return Axios.post('commande/confirm', {}, {params})
    .then(response => {
      // console.log('confirm')
      return response
    })
    .catch(function (error) {
      alert('Error of stock')
      console.log(error)
    })
}
export {listObject, deleteObject, updateObject, addObject, login, confirmCommande, logout}
