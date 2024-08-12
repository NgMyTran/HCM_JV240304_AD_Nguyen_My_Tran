package Exam_Advance_1.ra.controller;

import Exam_Advance_1.ra.model.Catalog;
import Exam_Advance_1.ra.service.CatalogService;
import Exam_Advance_1.ra.service.IService;

import java.util.List;

public class CatalogController implements IService<Catalog,Integer> {
    private CatalogService catalogService = new CatalogService();

    @Override
    public List<Catalog> getAll() {
        return catalogService.getAll();
    }

    @Override
    public void save(Catalog catalog) {
        catalogService.save(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        return catalogService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        catalogService.delete(id);
    }

    public int getNewId() {
        return catalogService.getNewId();
    }
}
