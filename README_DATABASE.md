# Cập Nhật SQLite cho Ứng Dụng Quản Lý Sinh Viên

## Tóm Tắt Các Thay Đổi

Project quản lý sinh viên đã được cập nhật để lưu trữ dữ liệu vào cơ sở dữ liệu SQLite sử dụng Room Database (thư viện chính thức của Android).

## Các File Mới Được Tạo

### 1. **StudentDao.kt** (Data Access Object)
- Interface định nghĩa các phương thức truy vấn database
- Các phương thức:
  - `getAllStudents()`: Lấy tất cả sinh viên (LiveData)
  - `insert()`: Thêm sinh viên mới
  - `update()`: Cập nhật thông tin sinh viên
  - `delete()`: Xóa sinh viên
  - `deleteAll()`: Xóa tất cả sinh viên
  - `getStudentById()`: Tìm sinh viên theo ID

### 2. **StudentDatabase.kt**
- Lớp database chính sử dụng Room
- Quản lý kết nối database với pattern Singleton
- Tên database: `student_database`

### 3. **StudentRepository.kt**
- Lớp trung gian giữa ViewModel và Database
- Thực hiện các thao tác dữ liệu

## Các File Được Cập Nhật

### 1. **StudentModel.kt**
- Thêm annotations Room: `@Entity` và `@PrimaryKey`
- Biến thành entity của Room database
- Table name: `students`

### 2. **StudentViewModel.kt**
- Thay đổi từ `ViewModel` thành `AndroidViewModel`
- Tích hợp với Repository và Database
- Sử dụng `viewModelScope` cho coroutines
- Tất cả thao tác với database là bất đồng bộ

### 3. **StudentAdapter.kt**
- Cập nhật để làm việc với List thay vì MutableList
- Callbacks nhận StudentModel thay vì position
- Thêm method `updateList()` để cập nhật danh sách

### 4. **StudentListFragment.kt**
- Cập nhật adapter callbacks để truyền StudentModel
- Observe LiveData từ ViewModel

### 5. **UpdateStudentFragment.kt**
- Đơn giản hóa logic update
- Không cần tìm position nữa

### 6. **MainActivity.kt**
- Sử dụng `viewModels()` delegate
- Observe LiveData từ database
- Cập nhật logic CRUD operations

### 7. **build.gradle.kts**
- Thêm Room dependencies
- Thêm KSP (Kotlin Symbol Processing) plugin
- Thêm Navigation và ViewModel dependencies
- Bật ViewBinding

### 8. **libs.versions.toml**
- Thêm Room version: 2.6.1
- Thêm KSP version: 2.2.0-1.0.29
- Thêm Room libraries vào catalog

## Hướng Dẫn Sử Dụng

### Bước 1: Sync Project
1. Mở Android Studio
2. Chọn File → Sync Project with Gradle Files
3. Đợi tải dependencies (Room, KSP, Navigation, etc.)

### Bước 2: Build Project
```bash
./gradlew build
```
hoặc trong Android Studio: Build → Rebuild Project

### Bước 3: Run Application
- Chọn emulator hoặc thiết bị
- Nhấn Run (Shift+F10)

## Tính Năng

### Lưu Trữ Bền Vững
- Dữ liệu sinh viên được lưu trong SQLite database
- Dữ liệu vẫn còn sau khi đóng app
- Tự động đồng bộ giữa database và UI

### CRUD Operations
1. **Create**: Thêm sinh viên mới vào database
2. **Read**: Hiển thị danh sách sinh viên từ database
3. **Update**: Cập nhật thông tin sinh viên
4. **Delete**: Xóa sinh viên khỏi database

### Architecture
- **MVVM Pattern**: Model-View-ViewModel
- **Room Database**: SQLite wrapper
- **LiveData**: Reactive data updates
- **Coroutines**: Asynchronous operations
- **Repository Pattern**: Data access abstraction

## Cấu Trúc Database

### Table: students
| Column | Type   | Constraint  |
|--------|--------|-------------|
| id     | TEXT   | PRIMARY KEY |
| name   | TEXT   | NOT NULL    |

## Lưu Ý Quan Trọng

### Dependencies
Project sử dụng các dependencies sau:
- Room Runtime: 2.6.1
- Room KTX: 2.6.1
- Room Compiler: 2.6.1 (KSP)
- Kotlin Coroutines
- ViewModel KTX
- Navigation Component

### Threading
- Tất cả database operations chạy trên background thread
- Sử dụng `viewModelScope` để quản lý lifecycle
- LiveData tự động cập nhật UI trên Main thread

### Database Location
Database được lưu tại:
```
/data/data/com.example.android_week10_ex1/databases/student_database
```

## Troubleshooting

### Lỗi "Unresolved reference room"
- Giải pháp: Sync Project with Gradle Files

### Lỗi Build
- Clean Project: Build → Clean Project
- Rebuild: Build → Rebuild Project
- Invalidate Caches: File → Invalidate Caches / Restart

### Database bị lỗi
- Uninstall app và cài lại để tạo database mới
- Hoặc tăng database version trong StudentDatabase.kt

## Mở Rộng Trong Tương Lai

### Có thể thêm:
1. **Migration Strategy**: Xử lý cập nhật database schema
2. **Export/Import**: Xuất/nhập dữ liệu
3. **Search**: Tìm kiếm sinh viên
4. **Sort**: Sắp xếp theo tên hoặc ID
5. **Validation**: Kiểm tra dữ liệu đầu vào
6. **More Fields**: Thêm email, phone, address, etc.

## Liên Hệ

Nếu có câu hỏi hoặc gặp vấn đề, vui lòng tạo issue hoặc liên hệ với developer.

---
**Phiên bản**: 1.0
**Ngày cập nhật**: December 29, 2025

