# Hướng dẫn Ứng dụng Android

## Tổng quan
Dự án này bao gồm 2 ứng dụng chính:
1. **Calculator (Máy tính)** - Bài 1
2. **Registration Form (Biểu mẫu đăng ký)** - Bài 2

## Cấu trúc dự án

### 1. MainActivity
- File: `app/src/main/java/com/example/hoc/MainActivity.kt`
- Layout: `app/src/main/res/layout/activity_main.xml`
- Chức năng: Màn hình chính với 2 nút để chọn ứng dụng

### 2. Calculator Activity (Bài 1)
- File: `app/src/main/java/com/example/hoc/CalcActivity.kt`
- Layout: `app/src/main/res/layout/calc.xml`

#### Chức năng:
- **Phép tính số nguyên**: Hỗ trợ +, -, x, /
- **Nút BS (Backspace)**: Xóa chữ số hàng đơn vị của toán hạng hiện tại
- **Nút CE (Clear Entry)**: Xóa giá trị toán hạng hiện tại về 0
- **Nút C (Clear)**: Xóa toàn bộ phép toán, bắt đầu lại từ đầu
- **Nút +/-**: Đổi dấu số hiện tại
- **Nút . (Dot)**: Nhập số thập phân
- **Nút = (Equals)**: Tính toán kết quả

#### Cách hoạt động:
- Nhập số → Chọn phép tính (+, -, x, /) → Nhập số tiếp theo → Nhấn =
- BS: Xóa từng chữ số từ phải sang trái
- CE: Xóa số hiện tại về 0 nhưng giữ lại phép tính
- C: Reset toàn bộ calculator
- Xử lý chia cho 0: Hiển thị "Error"
- Kết quả số nguyên: Tự động loại bỏ phần thập phân nếu kết quả là số nguyên

### 3. Registration Form Activity (Bài 2)
- File: `app/src/main/java/com/example/hoc/InfoActivity.kt`
- Layout: `app/src/main/res/layout/info.xml`

#### Chức năng:
- **First Name & Last Name**: Nhập họ và tên
- **Gender**: Chọn giới tính (Male/Female) bằng RadioButton
- **Birthday**: Chọn ngày sinh
  - Nhấn nút "Select" để hiện/ẩn CalendarView
  - Chọn ngày trên CalendarView tự động cập nhật vào EditText
  - Nhấn lại "Select" để ẩn CalendarView
- **Address**: Nhập địa chỉ (multiline)
- **Email**: Nhập email
- **Terms of Use**: Checkbox đồng ý điều khoản

#### Validation khi nhấn Register:
- Kiểm tra tất cả trường thông tin
- Nếu trường nào chưa điền → Đổi màu nền thành **đỏ**
- Nếu chưa chọn giới tính hoặc chưa check Terms → Hiển thị Toast message
- Nếu tất cả hợp lệ → Hiển thị "Registration Successful!"

## Cài đặt và Chạy

### Yêu cầu:
- Android Studio
- Android SDK 24 trở lên
- Kotlin support

### Các bước:
1. Mở project trong Android Studio
2. Sync Gradle (Android Studio sẽ tự động sync)
3. Chạy app trên emulator hoặc thiết bị thật

### Build Gradle:
File `app/build.gradle.kts` đã được cấu hình với:
- ConstraintLayout 2.1.4
- AppCompat 1.6.1
- Material Design 1.11.0

## Cấu trúc Files

```
app/src/main/
├── java/com/example/hoc/
│   ├── MainActivity.kt          # Màn hình chính
│   ├── CalcActivity.kt         # Calculator
│   └── InfoActivity.kt         # Registration Form
├── res/
│   ├── layout/
│   │   ├── activity_main.xml   # Layout chính
│   │   ├── calc.xml           # Layout calculator
│   │   └── info.xml           # Layout registration form
│   └── values/
│       └── colors.xml         # Định nghĩa màu sắc
└── AndroidManifest.xml        # Khai báo activities
```

## Màu sắc (colors.xml)
- `ResultBg`: #e1e1e1 (Màu nền hiển thị kết quả)
- `OperatorButtonBg`: #dedede (Màu nền nút toán tử)
- `NumberButtonBg`: #efefef (Màu nền nút số)

## Lưu ý
- Calculator hỗ trợ cả số nguyên và số thập phân
- Kết quả tự động chuyển về số nguyên nếu không có phần thập phân
- Registration form có ScrollView để hỗ trợ scroll khi CalendarView hiển thị
- Validation form đổi màu nền trường lỗi thành đỏ (#FF0000)
- Màu nền mặc định của EditText: #E0E0E0

## Tính năng bổ sung có thể mở rộng
- Lưu lịch sử tính toán cho Calculator
- Lưu dữ liệu đăng ký vào database
- Thêm validation email format
- Thêm các phép tính nâng cao (%, √, ^)
