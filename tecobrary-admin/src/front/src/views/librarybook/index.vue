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
      <el-table-column align="center" label="번호" width="100">
        <span slot-scope="scope">{{ scope.row.id }}</span>
      </el-table-column>
      <el-table-column align="center" label="제목">
        <span slot-scope="scope" class="link-type" @click="onClickTitle(scope.row.id)">{{ scope.row.title }}</span>
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
        :total="libraryBooks.totalElements"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    
    <!-- 다이얼로그 -->
    <el-dialog title="도서 상세 정보" :visible.sync="dialog.visible">
      <div style="display: flex; flex-direction: row; width: 100%;">
        <div style="flex: 2;">
          <el-form :model="libraryBookDetail">
            <el-form-item label="제목" label-width="120px">
              <span style="padding-left: 16px;">{{libraryBookDetail.title}}</span>
            </el-form-item>
            <el-form-item label="저자" label-width="120px">
              <span style="padding-left: 16px;">{{libraryBookDetail.author}}</span>
            </el-form-item>
            <el-form-item label="출판사" label-width="120px">
              <span style="padding-left: 16px;">{{libraryBookDetail.publisher}}</span>
            </el-form-item>
            <el-form-item label="ISBN" label-width="120px">
              <span style="padding-left: 16px;">{{libraryBookDetail.isbn}}</span>
            </el-form-item>
            <el-form-item label="책 설명" label-width="120px">
              <span style="padding-left: 16px;">{{libraryBookDetail.description}}</span>
            </el-form-item>
          </el-form>
        </div>
        <div style="flex: 1;">
          <img :src="libraryBookDetail.image" style="height: 100%;"/>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialog.visible = false">Cancel</el-button>
        <el-button type="primary" @click="dialog.visible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {getLibraryBooks, getLibraryBook} from "@/api/libraryBook";

  export default {
    data() {
      return {
        queryForm: {
          title: '',
          author: '',
          publisher: '',
          isbn: '',
          page: 0,
          size: 10
        },

        libraryBooks: {
          totalElements: 1,
          totalPages: 1,
          number: 1,
          content: []
        },

        dialog: {
          visible: false
        },

        libraryBookDetail: {
          id: '',
          image: '',
          title: '',
          author: '',
          publisher: '',
          isbn: '',
          description: '',
        }
      }
    },

    async beforeMount() {
      try {
        const response = await getLibraryBooks(this.queryForm)
        const {content, totalElements, totalPages, number} = response
        this.libraryBooks.content = content
        this.libraryBooks.totalElements = totalElements
        this.libraryBooks.totalPages = totalPages
        this.libraryBooks.number = number
      } catch (e) {
        console.log(e)
      }
    },

    methods: {
      async onSubmit() {
        try {
          const response = await getLibraryBooks(this.queryForm)
          const {content, totalElements, totalPages, number} = response
          this.libraryBooks.content = content
          this.libraryBooks.totalElements = totalElements
          this.libraryBooks.totalPages = totalPages
          this.libraryBooks.number = number
        } catch (e) {
          console.log(e)
        }
      },

      async onClickTitle(id) {
        this.dialog.visible = true
        try {
          const response = await getLibraryBook(id)
          this.libraryBookDetail = response
        } catch (e) {
          console.log(e)
        }
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
