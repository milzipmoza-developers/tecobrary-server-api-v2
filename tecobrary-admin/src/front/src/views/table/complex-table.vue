<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" class="filter-item" placeholder="Title" style="width: 200px;"
                v-model="listQuery.title"/>
      <el-select class="filter-item" clearable placeholder="Imp" style="width: 90px" v-model="listQuery.importance">
        <el-option :key="item" :label="item" :value="item" v-for="item in importanceOptions"/>
      </el-select>
      <el-select class="filter-item" clearable placeholder="Type" style="width: 130px" v-model="listQuery.type">
        <el-option :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key"
                   v-for="item in calendarTypeOptions"/>
      </el-select>
      <el-select @change="handleFilter" class="filter-item" style="width: 140px" v-model="listQuery.sort">
        <el-option :key="item.key" :label="item.label" :value="item.key" v-for="item in sortOptions"/>
      </el-select>
      <el-button @click="handleFilter" class="filter-item" icon="el-icon-search" type="primary" v-waves>
        Search
      </el-button>
      <el-button @click="handleCreate" class="filter-item" icon="el-icon-edit" style="margin-left: 10px;"
                 type="primary">
        Add
      </el-button>
      <el-button :loading="downloadLoading" @click="handleDownload" class="filter-item" icon="el-icon-download"
                 type="primary" v-waves>
        Export
      </el-button>
      <el-checkbox @change="tableKey=tableKey+1" class="filter-item" style="margin-left:15px;" v-model="showReviewer">
        reviewer
      </el-checkbox>
    </div>

    <el-table
      :data="list"
      :key="tableKey"
      @sort-change="sortChange"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      v-loading="listLoading"
    >
      <el-table-column :class-name="getSortClass('id')" align="center" label="ID" prop="id" sortable="custom"
                       width="80">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Date" width="150px">
        <template slot-scope="{row}">
          <span>{{ row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Title" min-width="150px">
        <template slot-scope="{row}">
          <span @click="handleUpdate(row)" class="link-type">{{ row.title }}</span>
          <el-tag>{{ row.type | typeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Author" width="110px">
        <template slot-scope="{row}">
          <span>{{ row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Reviewer" v-if="showReviewer" width="110px">
        <template slot-scope="{row}">
          <span style="color:red;">{{ row.reviewer }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Imp" width="80px">
        <template slot-scope="{row}">
          <svg-icon :key="n" class="meta-item__icon" icon-class="star" v-for="n in + row.importance"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Readings" width="95">
        <template slot-scope="{row}">
          <span @click="handleFetchPv(row.pageviews)" class="link-type" v-if="row.pageviews">{{ row.pageviews }}</span>
          <span v-else>0</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="Actions" width="230">
        <template slot-scope="{row,$index}">
          <el-button @click="handleUpdate(row)" size="mini" type="primary">
            Edit
          </el-button>
          <el-button @click="handleModifyStatus(row,'published')" size="mini" type="success"
                     v-if="row.status!='published'">
            Publish
          </el-button>
          <el-button @click="handleModifyStatus(row,'draft')" size="mini" v-if="row.status!='draft'">
            Draft
          </el-button>
          <el-button @click="handleDelete(row,$index)" size="mini" type="danger" v-if="row.status!='deleted'">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination :limit.sync="listQuery.limit" :page.sync="listQuery.page" :total="total" @pagination="getList"
                v-show="total>0"/>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="temp" :rules="rules" label-position="left" label-width="70px" ref="dataForm"
               style="width: 400px; margin-left:50px;">
        <el-form-item label="Type" prop="type">
          <el-select class="filter-item" placeholder="Please select" v-model="temp.type">
            <el-option :key="item.key" :label="item.display_name" :value="item.key"
                       v-for="item in calendarTypeOptions"/>
          </el-select>
        </el-form-item>
        <el-form-item label="Date" prop="timestamp">
          <el-date-picker placeholder="Please pick a date" type="datetime" v-model="temp.timestamp"/>
        </el-form-item>
        <el-form-item label="Title" prop="title">
          <el-input v-model="temp.title"/>
        </el-form-item>
        <el-form-item label="Status">
          <el-select class="filter-item" placeholder="Please select" v-model="temp.status">
            <el-option :key="item" :label="item" :value="item" v-for="item in statusOptions"/>
          </el-select>
        </el-form-item>
        <el-form-item label="Imp">
          <el-rate :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max="3" style="margin-top:8px;"
                   v-model="temp.importance"/>
        </el-form-item>
        <el-form-item label="Remark">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" placeholder="Please input" type="textarea"
                    v-model="temp.remark"/>
        </el-form-item>
      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button @click="dialogStatus==='create'?createData():updateData()" type="primary">
          Confirm
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column label="Channel" prop="key"/>
        <el-table-column label="Pv" prop="pv"/>
      </el-table>
      <span class="dialog-footer" slot="footer">
        <el-button @click="dialogPvVisible = false" type="primary">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {createArticle, fetchList, fetchPv, updateArticle} from '@/api/article'
  import waves from '@/directive/waves' // waves directive
  import {parseTime} from '@/utils'
  import Pagination from '@/components/Pagination' // secondary package based on el-pagination

  const calendarTypeOptions = [
    {key: 'CN', display_name: 'China'},
    {key: 'US', display_name: 'USA'},
    {key: 'JP', display_name: 'Japan'},
    {key: 'EU', display_name: 'Eurozone'}
  ]

  // arr to obj, such as { CN : "China", US : "USA" }
  const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})

  export default {
    name: 'ComplexTable',
    components: {Pagination},
    directives: {waves},
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      },
      typeFilter(type) {
        return calendarTypeKeyValue[type]
      }
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          importance: undefined,
          title: undefined,
          type: undefined,
          sort: '+id'
        },
        importanceOptions: [1, 2, 3],
        calendarTypeOptions,
        sortOptions: [{label: 'ID Ascending', key: '+id'}, {label: 'ID Descending', key: '-id'}],
        statusOptions: ['published', 'draft', 'deleted'],
        showReviewer: false,
        temp: {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          type: '',
          status: 'published'
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        dialogPvVisible: false,
        pvData: [],
        rules: {
          type: [{required: true, message: 'type is required', trigger: 'change'}],
          timestamp: [{type: 'date', required: true, message: 'timestamp is required', trigger: 'change'}],
          title: [{required: true, message: 'title is required', trigger: 'blur'}]
        },
        downloadLoading: false
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.data.items
          this.total = response.data.total

          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      handleModifyStatus(row, status) {
        this.$message({
          message: '操作Success',
          type: 'success'
        })
        row.status = status
      },
      sortChange(data) {
        const {prop, order} = data
        if (prop === 'id') {
          this.sortByID(order)
        }
      },
      sortByID(order) {
        if (order === 'ascending') {
          this.listQuery.sort = '+id'
        } else {
          this.listQuery.sort = '-id'
        }
        this.handleFilter()
      },
      resetTemp() {
        this.temp = {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          status: 'published',
          type: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
            this.temp.author = 'vue-element-admin'
            createArticle(this.temp).then(() => {
              this.list.unshift(this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: 'Success',
                message: 'Created Successfully',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.temp.timestamp = new Date(this.temp.timestamp)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
            updateArticle(tempData).then(() => {
              const index = this.list.findIndex(v => v.id === this.temp.id)
              this.list.splice(index, 1, this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: 'Success',
                message: 'Update Successfully',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row, index) {
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
        this.list.splice(index, 1)
      },
      handleFetchPv(pv) {
        fetchPv(pv).then(response => {
          this.pvData = response.data.pvData
          this.dialogPvVisible = true
        })
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
          const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
          const data = this.formatJson(filterVal)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'table-list'
          })
          this.downloadLoading = false
        })
      },
      formatJson(filterVal) {
        return this.list.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      },
      getSortClass: function (key) {
        const sort = this.listQuery.sort
        return sort === `+${key}` ? 'ascending' : 'descending'
      }
    }
  }
</script>
