#include "HueSaturation.h"
#include <iostream>
#include <vector>
#include <cmath>
using namespace std;


// Function to perform hue and saturation adjustment using a convolution matrix
void applyHueSaturation(vector< vector<Pixel> > &imageVector, float saturationValue, float hueValue) {
    // Define the convolution matrix for hue and saturation adjustments
    float matrix[3][3] = {
        {0.299, 0.587, 0.114},
        {0.299, 0.587, 0.114},
        {0.299, 0.587, 0.114}
    };

    // Loop through each pixel in the image vector
    for (int i = 0; i < imageVector.size(); ++i) {
        for (int j = 0; j < imageVector[i].size(); ++j) {
            float r = 0, g = 0, b = 0;
    
            // Apply convolution to calculate new RGB values
            for (int m = -1; m <= 1; ++m) {
                for (int n = -1; n <= 1; ++n) {
                    int x = i + m;
                    int y = j + n;

                    // Check boundary conditions
                    if (x >= 0 && x < imageVector.size() && y >= 0 && y < imageVector[i].size()) {
                        r += imageVector[x][y].r * matrix[m + 1][n + 1];
                        g += imageVector[x][y].g * matrix[m + 1][n + 1];
                        b += imageVector[x][y].b * matrix[m + 1][n + 1];
                    }
                }
            }

            // Hue adjustment
            float h = atan2(b - g, sqrt((r - g) * (r - g) + (b - g) * (r - b))) * (180.0 / M_PI) + hueValue;

            // Saturation adjustment
            float s = sqrt((r - g) * (r - g) + (b - g) * (r - b) + (b - g) * (b - g));

            // Update pixel values
            imageVector[i][j].r = s * sin(h) + g;
            imageVector[i][j].g = s * cos(h) + g;
            imageVector[i][j].b = s * cos(h) + b;
        }
    }
}