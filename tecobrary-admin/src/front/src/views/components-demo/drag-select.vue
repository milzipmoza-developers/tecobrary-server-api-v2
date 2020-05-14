<template>
  <div class="components-container">
    <el-drag-select multiple placeholder="请选择" style="width:500px;" v-model="value">
      <el-option :key="item.value" :label="item.label" :value="item.value" v-for="item in options"/>
    </el-drag-select>

    <div style="margin-top:30px;">
      <el-tag :key="item" style="margin-right:15px;" v-for="item of value">
        {{ item }}
      </el-tag>
    </div>
  </div>
</template>

<script>
  import ElDragSelect from '@/components/DragSelect' // base on element-ui

  export default {
    name: 'DragSelectDemo',
    components: {ElDragSelect},
    data() {
      return {
        value: ['Apple', 'Banana', 'Orange'],
        options: [{
          value: 'Apple',
          label: 'Apple'
        }, {
          value: 'Banana',
          label: 'Banana'
        }, {
          value: 'Orange',
          label: 'Orange'
        }, {
          value: 'Pear',
          label: 'Pear'
        }, {
          value: 'Strawberry',
          label: 'Strawberry'
        }]
      }
    }
  }
</script>
