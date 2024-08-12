package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.controller.CatalogController;
import Exam_Advance_1.ra.controller.ProductController;
import Exam_Advance_1.ra.model.Catalog;
import Exam_Advance_1.ra.model.Product;
import Exam_Advance_1.ra.util.InputMethods;

import java.util.List;

public class BookManagement {
    static ProductController productController = new ProductController();
    static CatalogController catalogController = new CatalogController();
    public static void main(String[] args) {
		List<Catalog> listCat = catalogController.getAll();
		Catalog cat1 = new Catalog(1,"Truyện tranh");
		Catalog cat2 = new Catalog(2,"Truyện chữ");
		listCat.add(cat1);
		listCat.add(cat2);
		List<Product> listPro = productController.getAll();
		Product p1 =  new Product("P0001","Kingdom",100,"",100,cat1,true);
		Product p2 =  new Product("P0002","Dòng sông hueyefn bí",150,"",120,cat1,true);
		Product p3 =  new Product("P0003","Đồ hoa",160,"",80,cat2,true);
		Product p4 =  new Product("P0004","hoa học trò",200,"",90,cat2,true);
		listPro.add(p1);
		listPro.add(p2);
		listPro.add(p3);
		listPro.add(p4);
        while (true) {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục \n" +
                    "2. Quản lý sản phẩm \n" +
                    "3. Menu User\n" +
                    "4. Thoát ");
            System.out.print("Mời bạn lựa chọn: ");
            int choose = InputMethods.getInteger();
            switch (choose) {
                case 1:
                    new CatalogManagement(catalogController);
                    break;
                case 2:
                    new ProductManagement(productController, catalogController);
                    break;
                case 3:
                    new MenuUser(productController);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập lại (1 -> 3)");
                    break;
            }
        }
    }
}
