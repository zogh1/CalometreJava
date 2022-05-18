/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.Post;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface IServicePost {
     public void createPost(Post p);
    public List<Post> readPost();
    
}
