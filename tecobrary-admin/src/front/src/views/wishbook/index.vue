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
      empty-text="희망도서가 없습니다."
      :data="wishBooks.content"
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
      <el-table-column align="center" label="신청자">
        <span slot-scope="scope">{{ scope.row.requestUser }}</span>
      </el-table-column>
      <el-table-column align="center" label="신청일자">
        <span slot-scope="scope">{{ scope.row.createdAt }}</span>
      </el-table-column>
    </el-table>
  
    <!--  페이지네이션  -->
    <div class="block" style="margin: 16px;">
      <el-pagination
        :current-page="queryForm.page"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="queryForm.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="wishBooks.totalElements"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {getWishBooks} from "@/api/wishBook";

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
        
        wishBooks: {
          totalElements: 1,
          totalPages: 1,
          number: 1,
          content: []
        },
      }
    },
    
    async beforeMount() {
      await this.fetchWishBooks()
    },
    
    methods: {
      async fetchWishBooks() {
        try {
          const response = await getWishBooks(this.queryForm)
          const {content, totalElements, totalPages, number} = response
          this.wishBooks.content = content
          this.wishBooks.totalElements = totalElements
          this.wishBooks.totalPages = totalPages
          this.wishBooks.number = number
          console.log(this.wishBooks)
        } catch (e) {
          console.log(e)
        }
      },

      async onSubmit() {
        await this.fetchWishBooks()
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