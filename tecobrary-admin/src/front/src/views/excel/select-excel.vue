<template>
  <div class="app-container">
    <el-input placeholder="Please enter the file name (default excel-list)" prefix-icon="el-icon-document"
              style="width:350px;" v-model="filename"/>
    <el-button :loading="downloadLoading" @click="handleDownload" icon="el-icon-document" style="margin-bottom:20px"
               type="primary">
      Export Selected Items
    </el-button>
    <a href="https://panjiachen.github.io/vue-element-admin-site/feature/component/excel.html" style="margin-left:15px;"
       target="_blank">
      <el-tag type="info">Documentation</el-tag>
    </a>
    <el-table
      :data="list"
      @selection-change="handleSelectionChange"
      border
      element-loading-text="拼命加载中"
      fit
      highlight-current-row
      ref="multipleTable"
      v-loading="listLoading"
    >
      <el-table-column align="center" type="selection"/>
      <el-table-column align="center" label="Id" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Title">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="Author" width="110">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.author }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Readings" width="115">
        <template slot-scope="scope">
          {{ scope.row.pageviews }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="PDate" width="220">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ scope.row.display_time }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {fetchList} from '@/api/article'

  export default {
    name: 'SelectExcel',
    data() {
      return {
        list: null,
        listLoading: true,
        multipleSelection: [],
        downloadLoading: false,
        filename: ''
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.data.items
          this.listLoading = false
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      handleDownload() {
        if (this.multipleSelection.length) {
          this.downloadLoading = true
          import('@/vendor/Export2Excel').then(excel => {
            const tHeader = ['Id', 'Title', 'Author', 'Readings', 'Date']
            const filterVal = ['id', 'title', 'author', 'pageviews', 'display_time']
            const list = this.multipleSelection
            const data = this.formatJson(filterVal, list)
            excel.export_json_to_excel({
              header: tHeader,
              data,
              filename: this.filename
            })
            this.$refs.multipleTable.clearSelection()
            this.downloadLoading = false
          })
        } else {
          this.$message({
            message: 'Please select at least one item',
            type: 'warning'
          })
        }
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      }
    }
  }
</script>
