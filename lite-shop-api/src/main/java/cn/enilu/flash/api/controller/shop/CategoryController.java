package cn.enilu.flash.api.controller.shop;

import cn.enilu.flash.web.controller.BaseController;
import cn.enilu.flash.bean.entity.shop.Category;
import cn.enilu.flash.service.shop.CategoryService;

import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.dictmap.CommonDict;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Rets;

import cn.enilu.flash.utils.factory.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/category")
public class CategoryController extends BaseController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list() {
	Page<Category> page = new PageFactory<Category>().defaultPage();
		page = categoryService.queryPage(page);
		return Rets.success(page);
	}
	@RequestMapping(method = RequestMethod.POST)
	@BussinessLog(value = "编辑商品类别", key = "name",dict= CommonDict.class)
	public Object save(@ModelAttribute Category tShopCategory){
		if(tShopCategory.getId()==null){
			categoryService.insert(tShopCategory);
		}else {
			categoryService.update(tShopCategory);
		}
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	@BussinessLog(value = "删除商品类别", key = "id",dict= CommonDict.class)
	public Object remove(Long id){
		if (id == null) {
			throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
		}
		categoryService.delete(id);
		return Rets.success();
	}
}
