import Axios from 'axios'
import router from '../router'
function listObject (table) {
  return Axios.get(table + '/list')
    .then(response => {
      console.log('response.status=' + response.status)
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function login (params) {
  Axios.post('/login', {}, {params})
    .then(response => {
      console.log('response.status1=' + response.status)
      if (response.status === 200) {
        router.push('/client/list')
      }
    }).catch(function (error) {
      alert('user not found!')
      console.log(error)
    })
}
function deleteObject (table, params) {
  return Axios.delete(table + '/delete', {params})
    .then(response => {
      console.log('response.status=' + response.status)
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function updateObject (table, params) {
  return Axios.post(table + '/update', {}, {params})
    .then(response => {
      console.log('response.status=' + response.status)
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
function addObject (table, params) {
  return Axios.post(table + '/add', {}, {params})
    .then(response => {
      console.log('response.status=' + response.status)
      return response
    })
    .catch(function (error) {
      console.log(error)
    })
}
export {listObject, deleteObject, updateObject, addObject, login}
