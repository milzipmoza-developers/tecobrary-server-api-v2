<template>
  <el-dropdown :show-timeout="100" trigger="click">
    <el-button plain>
      Link
      <i class="el-icon-caret-bottom el-icon--right"/>
    </el-button>
    <el-dropdown-menu class="no-padding no-border" slot="dropdown" style="width:400px">
      <el-form-item label-width="0px" prop="source_uri" style="margin-bottom: 0px">
        <el-input placeholder="Please enter the content" v-model="source_uri">
          <template slot="prepend">
            URL
          </template>
        </el-input>
      </el-form-item>
    </el-dropdown-menu>
  </el-dropdown>
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
      source_uri: {
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
