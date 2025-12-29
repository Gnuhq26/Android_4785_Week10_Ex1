# ✅ Checklist - Cập Nhật SQLite Database

## Trước khi chạy ứng dụng

### □ Step 1: Sync Gradle Project
1. Mở Android Studio
2. File → Sync Project with Gradle Files
3. Đợi quá trình sync hoàn tất (có thể mất vài phút)
4. Kiểm tra không có lỗi trong Build Output

### □ Step 2: Verify Dependencies
Kiểm tra các dependencies đã được thêm vào:
- ✅ Room Runtime 2.6.1
- ✅ Room KTX 2.6.1
- ✅ Room Compiler 2.6.1
- ✅ KSP Plugin
- ✅ Navigation Component
- ✅ ViewModel KTX

### □ Step 3: Build Project
1. Build → Clean Project
2. Build → Rebuild Project
3. Đợi build hoàn tất thành công

### □ Step 4: Verify Files
Kiểm tra các file mới đã được tạo:
- ✅ StudentDao.kt
- ✅ StudentDatabase.kt
- ✅ StudentRepository.kt

Kiểm tra các file đã được cập nhật:
- ✅ StudentModel.kt (có @Entity, @PrimaryKey)
- ✅ StudentViewModel.kt (extends AndroidViewModel)
- ✅ StudentAdapter.kt (updateList method)
- ✅ build.gradle.kts (Room dependencies)
- ✅ libs.versions.toml (Room version)

## Khi chạy ứng dụng

### □ Test 1: Add Student
1. Nhập Student ID (ví dụ: "SV001")
2. Nhập Student Name (ví dụ: "Nguyen Van A")
3. Click Add/Submit button
4. Kiểm tra student xuất hiện trong RecyclerView

### □ Test 2: Data Persistence
1. Thêm 2-3 students
2. Close app hoàn toàn (swipe away from recent apps)
3. Mở lại app
4. **Kiểm tra**: Students vẫn còn đó! ✨

### □ Test 3: Update Student
1. Click vào một student trong list
2. Thay đổi tên
3. Click Update button
4. Kiểm tra tên đã được cập nhật

### □ Test 4: Delete Student
1. Click vào icon delete (thùng rác) bên cạnh student
2. Kiểm tra student đã bị xóa
3. Close và mở lại app
4. **Kiểm tra**: Student đã bị xóa vĩnh viễn

## Nếu có lỗi

### Lỗi: "Unresolved reference: room"
**Nguyên nhân**: Chưa sync Gradle
**Giải pháp**:
1. Click "Sync Now" trên banner màu vàng ở trên
2. Hoặc: File → Sync Project with Gradle Files

### Lỗi: "Cannot resolve symbol 'databinding'"
**Nguyên nhân**: ViewBinding chưa được build
**Giải pháp**:
1. Build → Rebuild Project
2. Đợi build hoàn tất

### Lỗi: Build failed
**Giải pháp**:
1. Build → Clean Project
2. Build → Rebuild Project
3. File → Invalidate Caches / Restart → Invalidate and Restart

### Lỗi: App crashes khi mở
**Nguyên nhân**: Database migration issue
**Giải pháp**:
1. Uninstall app từ emulator/device
2. Run app lại từ Android Studio
3. Database sẽ được tạo mới

### Lỗi: Compilation error về KSP
**Giải pháp**:
Kiểm tra version compatibility:
- Kotlin: 2.2.0
- KSP: 2.2.0-1.0.29
(Phải match với Kotlin version)

## Xác nhận thành công

✅ Project sync không có lỗi
✅ Build thành công
✅ App chạy trên emulator/device
✅ Có thể add student
✅ Student persist sau khi close app
✅ Có thể update student
✅ Có thể delete student
✅ UI tự động update khi có thay đổi

## Kiến trúc mới

```
┌─────────────────────┐
│  UI (Fragments)     │
│  - StudentList      │
│  - AddStudent       │
│  - UpdateStudent    │
└──────────┬──────────┘
           │ observe LiveData
           ↓
┌─────────────────────┐
│  StudentViewModel   │
│  - addStudent()     │
│  - updateStudent()  │
│  - deleteStudent()  │
└──────────┬──────────┘
           │ uses
           ↓
┌─────────────────────┐
│ StudentRepository   │
│ - insert()          │
│ - update()          │
│ - delete()          │
└──────────┬──────────┘
           │ calls
           ↓
┌─────────────────────┐
│    StudentDao       │
│  (Room Interface)   │
└──────────┬──────────┘
           │ accesses
           ↓
┌─────────────────────┐
│  StudentDatabase    │
│  (Room Database)    │
└──────────┬──────────┘
           │ stores in
           ↓
┌─────────────────────┐
│  SQLite Database    │
│  (students table)   │
└─────────────────────┘
```

## Tài liệu tham khảo

- **README_DATABASE.md**: Tài liệu chi tiết bằng tiếng Việt
- **SQLITE_UPDATE_SUMMARY.md**: Tổng quan bằng tiếng Anh

## Hỗ trợ

Nếu gặp vấn đề không có trong checklist này:
1. Đọc log errors trong Logcat
2. Kiểm tra lại các bước đã làm
3. Thử clean và rebuild project
4. Restart Android Studio

---
**Tạo bởi**: GitHub Copilot
**Ngày**: December 29, 2025

