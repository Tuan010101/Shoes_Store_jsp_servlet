/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.LoadCart;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trinh
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from product\n"
                + "order by id desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Product> getAllProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
        		+ "where cateID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public Product getAllProductByID(String id) {
        String query = "select * from product\r\n"
        		+ "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();	
        String query = "select * from product\r\n"
        		+ "where [name] like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%"+txtSearch+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public Account login(String user, String pass) {
    	String query = "select * from account\r\n"
    			+ "where [user] = ? and pass = ?";
    	try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			System.out.println(query);
			while(rs.next()) {
				return new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return null;
    }
    
    public Account checkAccountExist(String user) {
    	String query = "select * from account\r\n"
    			+ "where [user] = ?";
    	try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public void signUp(String user, String pass) {
    	String query = "insert into Account ([user], pass, isSell, isAdmin)\r\n"
    			+ "values(?,?,0,0)	";
    	try {
    		conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.executeUpdate();
			System.out.println(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public List<Product> getAllProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
        		+ "where sell_ID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteProduct(String pid) throws Exception {
    	String query = "delete from product\r\n"
    			+ "where id = ?";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void insertProduct(String name, String image, String price, String title, String description, String category, int sellID) {
    	String query = "insert into product([name], [image], [price], [title], [description], [cateID], [sell_ID])\r\n"
    			+ "values (?,?,?,?,?,?,?)";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, image);
			ps.setString(3, price);
			ps.setString(4, title);
			ps.setString(5, description);
			ps.setString(6, category);
			ps.setInt(7, sellID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void editProduct(String name, String image, String price, String title, String description, String category, String pid) {
    	String query = "update product \r\n"
    			+ "set [name] = ?,\r\n"
    			+ "[image] = ?,\r\n"
    			+ "[price] = ?,\r\n"
    			+ "[title] = ?,\r\n"
    			+ "[description] = ?,\r\n"
    			+ "[cateID] = ?\r\n"
    			+ "where id = ?";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, image);
			ps.setString(3, price);
			ps.setString(4, title);
			ps.setString(5, description);
			ps.setString(6, category);
			ps.setString(7, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Cart checkAmountProduct(int aid, String pid) {
    	String query = "select * from Cart\r\n"
    			+ "where AccountID = ? and ProductID = ?";
    	try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, aid);
			ps.setString(2, pid);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Cart(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public void insertCartFirst(int aid, String pid) throws Exception {
		// TODO Auto-generated method stub
    	String query = "insert into Cart ([AccountID], [ProductID], [Amount])\r\n"
    			+ "values (?, ?, 1)";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, aid);
			ps.setString(2, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void updateAmountCart(int aid, String pid, int amount) {
    	String query = "update Cart\r\n"
    			+ "set \r\n"
    			+ "[Amount] = ?\r\n"
    			+ "where [AccountID] = ? and [ProductID] = ?";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setInt(2, aid);
			ps.setString(3, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    public List<LoadCart> loadProductInCart(int aid) {
        List<LoadCart> list = new ArrayList<>();	
        String query = "select product.[id], product.[name], product.[image], product.[price], Cart.[Amount] from product, Cart\r\n"
        		+ "where product.[id] = Cart.[ProductID] and Cart.[AccountID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LoadCart(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteProductInCart( int aid, String pid) throws Exception {
    	String query = "delete from Cart\r\n"
    			+ "where AccountID = ? and ProductID = ?";
    	try {
			conn = new DBContext().getConnection();//mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, aid);
			ps.setString(2, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();	
        String query = "select * from Account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();

        for (Product o : list) {
            System.out.println(o);
        }
    }
}
