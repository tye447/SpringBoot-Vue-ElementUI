<template>
  <div class="error">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'Error',
  data () {
    return {
      msg: 'Error'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
