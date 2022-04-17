prod.setId(result.getInt("id"));
prod.setName(result.getString("name"));
prod.setPrice(result.getDouble("price"));
prod.setDescription(result.getString("description"));
prod.setQuantity(result.getInt("quantity"));
prod.setImage(result.getString("image"));