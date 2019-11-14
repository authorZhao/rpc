package com.git.service;

/**
 * 测试接口
 */
public interface CartService {
  /**
   * 根据用户名获取购物车详情
   * @param userId
   * @return
   */
  String findCartByUserId(Long userId);
}
