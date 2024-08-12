package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.model.Catalog;
import Exam_Advance_1.ra.controller.CatalogController;
import Exam_Advance_1.ra.util.InputMethods;
import static Exam_Advance_1.ra.service.ProductService.products;

public class CatalogManagement {
    private CatalogController catalogController;

    public CatalogManagement(CatalogController catalogController) {
        this.catalogController = catalogController;

        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục\n" +
                    "2. Hiển thị thông tin tất cả các danh mục \n" +
                    "3. Sửa tên danh mục theo mã danh mục \n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) \n" +
                    "5. Quay lại");
            System.out.print("Mời bạn lựa chọn: ");
            int choose = InputMethods.getInteger();
            switch (choose) {
                case 1:
                    addNewCatalog();
                    break;
                case 2:
                    showListCatalog();
                    break;
                case 3:
                    editCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public void addNewCatalog() {
        System.out.print("Bạn muốn nhập vào nhiêu danh mục: ");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Danh mục thứ " + (i + 1));
            Catalog catalog = new Catalog();
            catalog.setCatalogId(catalogController.getNewId());
            catalog.inputData();
            catalogController.save(catalog);
        }
    }

    public void showListCatalog() {
        if (catalogController.getAll().isEmpty()) {
            System.err.println("Chưa có danh mục nào");
            return;
        }
        for (Catalog c : catalogController.getAll()) {
            System.out.println(c);
        }
    }

    public void editCatalog() {
        System.out.print("Nhập mã danh mục cần : ");
        int id = InputMethods.getInteger();
        Catalog catalog = catalogController.findById(id);
        if (catalog == null) {
            System.err.println("Không tồn tại danh mục này");
            return;
        }
        Catalog newCatalog = new Catalog();
        newCatalog.setCatalogId(catalog.getCatalogId());
        newCatalog.inputData();
        catalogController.save(newCatalog);
    }

    public void deleteCatalog() {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int id = InputMethods.getInteger();
        Catalog catalog = catalogController.findById(id);
        if (catalog != null) {
           if(products.stream().noneMatch(product -> product.getCatalog().getCatalogId() == id)){
            catalogController.delete(id);
            System.out.println("Xóa danh mục thành công.");
           } else {
               System.err.println("Không thể xóa danh mục đang có sản phẩm.");
           }
        }else {
            System.err.println("Không tìm thấy danh mục.");
        }
    }
}
