package com.scaler.productservicemorningbatch;

//@SpringBootTest
class ProductServiceApplicationTests {

    /*@Autowired
    ProductRepository productRepository;

    @Autowired
    ProductController productController;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void multiplyBy2(){
        int i=1;
        //arrange, assert and act - 3A's of Unit Testing
        i = i+1;
        assertEquals(2,i);
    }

    @Test
    void invalidProductIdExceptionTest(){
        assertThrows(InvalidProductIdException.class,
        () -> productController.getProductById(10000L));
        }*/

    /*@Test
    public void testQueries(){

        List<Product> products = productRepository.someRandomQuery(102L);



        for(Product productWithIdAndTitle : products){
            System.out.println(productWithIdAndTitle.getId());
            System.out.println(productWithIdAndTitle.getTitle());

        }

        System.out.println("DEBUG");

        Optional<Category> category = categoryRepository.findById(2L);


        if(category.isPresent()) {
            List<Product> optionalProduct = category.get().getProducts(); //Lazy Fetching Error will occur here as Product collection is not fetched by default.
            if (!optionalProduct.isEmpty()) {
                for (Product product : optionalProduct) {
                    System.out.println(product.getTitle());
                }
            } else {
                Optional<Product> pr =  productRepository.findByCategory(category.get());
                System.out.println("Lazy Fetching");
            }
        }


    }*/


}
