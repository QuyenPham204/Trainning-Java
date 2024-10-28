import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Book {
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }
}


class Customer {
    private String name;
    private String address;
    private String phone;

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}


class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}


class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Book book, int quantity) {
        items.add(new CartItem(book, quantity));
        book.reduceStock(quantity); // Giảm số lượng tồn kho
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public List<CartItem> getItems() {
        return items;
    }
}


class Order {
    private Customer customer;
    private Cart cart;

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
    }

    public void processOrder() {
        System.out.println("Đơn hàng của " + customer.getName() + " đã được xử lý.");
        System.out.println("Địa chỉ giao hàng: " + customer.getAddress());
        System.out.println("Số điện thoại: " + customer.getPhone());
        System.out.println("Tổng số tiền: " + cart.calculateTotal() + " VND");
    }

    public void sendEmailConfirmation() {
        System.out.println("Email xác nhận đã được gửi tới: " + customer.getName() + " (" + customer.getAddress() + ")");
    }
}


class BookStore {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}

public class OnlineBookStore {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BookStore store = new BookStore();
        Customer customer = null;
        Cart cart = new Cart();

        int choice;
        do {
            System.out.println("\n Menu Hệ Thống Bán Sách Online ");
            System.out.println("1. Nhập thông tin sách mới");
            System.out.println("2. Xem danh sách sách hiện có");
            System.out.println("3. Nhập thông tin khách hàng");
            System.out.println("4. Thêm sách vào giỏ hàng");
            System.out.println("5. Thanh toán đơn hàng");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Dòng trống sau nextInt()

            switch (choice) {
                case 1:
                    // Thêm sách mới
                    System.out.print("Nhập số lượng sách muốn thêm: ");
                    int numBooks = scanner.nextInt();
                    scanner.nextLine(); // Dòng trống

                    for (int i = 0; i < numBooks; i++) {
                        System.out.println("Nhập thông tin sách " + (i + 1) + ":");
                        System.out.print("Tên sách: ");
                        String title = scanner.nextLine();
                        System.out.print("Tác giả: ");
                        String author = scanner.nextLine();
                        System.out.print("Giá: ");
                        double price = scanner.nextDouble();
                        System.out.print("Số lượng tồn kho: ");
                        int stock = scanner.nextInt();
                        scanner.nextLine(); // Dòng trống

                        store.addBook(new Book(title, author, price, stock));
                    }
                    break;

                case 2:
                    // Xem danh sách sách
                    System.out.println("\nDanh sách sách hiện có:");
                    for (Book book : store.getBooks()) {
                        System.out.println("Tên: " + book.getTitle() + ", Tác giả: " + book.getAuthor() +
                                ", Giá: " + book.getPrice() + ", Tồn kho: " + book.getStock());
                    }
                    break;

                case 3:
                    // Nhập thông tin khách hàng
                    System.out.println("Nhập thông tin khách hàng:");
                    System.out.print("Tên khách hàng: ");
                    String name = scanner.nextLine();
                    System.out.print("Địa chỉ: ");
                    String address = scanner.nextLine();
                    System.out.print("Số điện thoại: ");
                    String phone = scanner.nextLine();
                    customer = new Customer(name, address, phone);
                    break;

                case 4:
                    // Thêm sách vào giỏ hàng
                    if (customer == null) {
                        System.out.println("Vui lòng nhập thông tin khách hàng trước.");
                    } else {
                        System.out.println("\nThêm sách vào giỏ hàng:");
                        for (Book book : store.getBooks()) {
                            System.out.print("Nhập số lượng muốn mua cho sách \"" + book.getTitle() +
                                    "\" (hiện có " + book.getStock() + "): ");
                            int quantity = scanner.nextInt();
                            if (quantity > 0 && quantity <= book.getStock()) {
                                cart.addItem(book, quantity);
                            } else {
                                System.out.println("Số lượng không hợp lệ hoặc không đủ hàng trong kho.");
                            }
                        }
                    }
                    break;

                case 5:
                    // Thanh toán đơn hàng
                    if (customer == null) {
                        System.out.println("Vui lòng nhập thông tin khách hàng trước.");
                    } else if (cart.getItems().isEmpty()) {
                        System.out.println("Giỏ hàng của bạn đang trống.");
                    } else {
                        Order order = new Order(customer, cart);
                        order.processOrder();
                        order.sendEmailConfirmation();
                    }
                    break;

                case 6:
                    // Thoát
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 6);

        scanner.close();
    }


}
