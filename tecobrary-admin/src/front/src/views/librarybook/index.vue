<template>
  <div class="app-container">
    <!--  검색부  -->
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="제목">
        <el-input v-model="queryForm.title"></el-input>
      </el-form-item>
      <el-form-item label="저자">
        <el-input v-model="queryForm.author"></el-input>
      </el-form-item>
      <el-form-item label="출판사">
        <el-input v-model="queryForm.publisher"></el-input>
      </el-form-item>
      <el-form-item label="isbn">
        <el-input v-model="queryForm.isbn"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">검색</el-button>
      </el-form-item>
    </el-form>
    
    <!--  책 리스트  -->
    <el-table
      :data="libraryBooks.content"
      style="width: 100%; min-height: 400px;"
      highlight-current-row
      fit
    >
      <el-table-column align="center" label="번호">
        <span slot-scope="scope">{{ scope.row.id }}</span>
      </el-table-column>
      <el-table-column align="center" label="제목">
        <span slot-scope="scope" class="link-type" @click="onClickTitle()">{{ scope.row.title }}</span>
      </el-table-column>
      <el-table-column align="center" label="작가">
        <span slot-scope="scope">{{ scope.row.author }}</span>
      </el-table-column>
      <el-table-column align="center" label="출판사">
        <span slot-scope="scope">{{ scope.row.publisher }}</span>
      </el-table-column>
      <el-table-column align="center" label="ISBN">
        <span slot-scope="scope">{{ scope.row.isbn }}</span>
      </el-table-column>
      <el-table-column align="center" label="보유 장서 수">
        <span slot-scope="scope">{{ scope.row.bookCounts }}</span>
      </el-table-column>
    </el-table>
    
    <!--  페이지네이션  -->
    <div class="block" style="margin: 16px;">
      <el-pagination
        :current-page="queryForm.page"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="queryForm.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="libraryBooks.totalElement"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    
    <!-- 다이얼로그 -->
    <el-dialog title="도서 상세 정보" :visible.sync="dialog.visible">
      <el-form :model="form">
        <el-form-item label="제목" label-width="120px">
        </el-form-item>
        <el-form-item label="저자" label-width="120px">
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialog.visible = false">Cancel</el-button>
        <el-button type="primary" @click="dialog.visible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        queryForm: {
          title: '',
          author: '',
          publisher: '',
          isbn: '',
          page: 1,
          size: 10
        },

        libraryBooks: {
          totalElement: 1,
          totalPage: 1,
          number: 1,
          content: [
            {
              id: 1,
              title: '객체지향의 사실과 오해',
              author: '조영호',
              publisher: '위키북스',
              isbn: '912380123890',
              bookCounts: 3
            },
            {
              id: 2,
              title: '객체지향의 사실과 오해',
              author: '조영호',
              publisher: '위키북스',
              isbn: '912380123890',
              bookCounts: 0
            }
          ]
        },

        dialog: {
          visible: false
        }
      }
    },

    async beforeMount() {
      try {
        const response = await getLibraryBooks(this.queryForm)
        console.log(response.data)
      } catch (e) {
        console.log(e)
      }
    },

    methods: {
      onSubmit() {
        console.log('submit!')
      },

      onClickTitle() {
        this.dialog.visible = true
      },

      handleSizeChange(size) {
        this.queryForm.size = size
      },

      handleCurrentChange(page) {
        this.queryForm.page = page
      }
    }
  }
</script>

<style scoped>
</style>
