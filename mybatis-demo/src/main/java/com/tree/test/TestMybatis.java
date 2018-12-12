package com.tree.test;

import java.util.List;

import com.tree.entity.Seed;
import com.tree.entity.Tree;
import com.tree.service.SeedService;
import com.tree.service.TreeService;
import com.tree.service.impl.SeedServiceImpl;
import com.tree.service.impl.TreeServiceImpl;

public class TestMybatis {

	public static void main(String[] args) {
		testTree();
		testSeed();
	}

	/**
	 * 测试树
	 * 
	 * 和seed产生关联
	 */
	private static void testTree() {
		TreeService service = new TreeServiceImpl();

		Tree tree = service.selectById(1);

		System.out.println(tree);

	}

	/**
	 * 测试种子
	 */
	private static void testSeed() {
		SeedService seedService = new SeedServiceImpl();

		Seed seed = seedService.selectById(1);
		System.out.println(seed);

		List<Seed> seeds = seedService.selectByTreeId(1);

		System.out.println(seeds);

	}

}
