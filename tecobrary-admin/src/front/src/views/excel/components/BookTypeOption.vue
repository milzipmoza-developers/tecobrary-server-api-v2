<template>
  <div style="display:inline-block;">
    <label class="radio-label">Book Type: </label>
    <el-select style="width:120px;" v-model="bookType">
      <el-option
        :key="item"
        :label="item"
        :value="item"
        v-for="item in options"
      />
    </el-select>
  </div>
</template>

<script>
  export default {
    props: {
      value: {
        type: String,
        default: 'xlsx'
      }
    },
    data() {
      return {
        options: ['xlsx', 'csv', 'txt']
      }
    },
    computed: {
      bookType: {
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
