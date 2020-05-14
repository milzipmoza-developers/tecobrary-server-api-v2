<template>
  <div style="display:inline-block;">
    <label class="radio-label" style="padding-left:0;">Filename: </label>
    <el-input placeholder="Please enter the file name (default excel-list)" prefix-icon="el-icon-document"
              style="width:345px;" v-model="filename"/>
  </div>
</template>

<script>
  export default {
    props: {
      value: {
        type: String,
        default: ''
      }
    },
    computed: {
      filename: {
        get() {
          return this.value
        },
        set(val) {
          this.$emit('input', val)
        }
      }
    }
  }
</script>
