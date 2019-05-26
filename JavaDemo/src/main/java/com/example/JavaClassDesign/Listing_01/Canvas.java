/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

package com.example.JavaClassDesign.Listing_01;

class Canvas {
    void getArea() {
        Circle circle = new Circle();
        // call to public method area(), outside package
        circle.area();
    }
}
