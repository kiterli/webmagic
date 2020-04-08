package com.crawler.dao;

import com.crawler.entity.Movie;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Movie)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-07 14:51:20
 */
public interface MovieDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Movie queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Movie> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param movie 实例对象
     * @return 对象列表
     */
    List<Movie> queryAll(Movie movie);

    /**
     * 新增数据
     *
     * @param movie 实例对象
     * @return 影响行数
     */
    int insert(Movie movie);

    /**
     * 添加类型
     * @param movieId
     * @param typeId
     * @return
     */
    int insertMovieType(@Param("movieId") int movieId,@Param("typeId")int typeId);

    /**
     * 修改数据
     *
     * @param movie 实例对象
     * @return 影响行数
     */
    int update(Movie movie);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}