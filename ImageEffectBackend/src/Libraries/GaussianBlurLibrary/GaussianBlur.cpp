#include "GaussianBlur.h"
#include <vector>
#include "../Pixel.h"
using namespace std;

void applyGaussianBlur(vector<vector<Pixel>> &img, float amount) {
    int radius = static_cast<int>(amount);

    // Generate Gaussian kernel
    vector<vector<float>> kernel(2 * radius + 1, vector<float>(2 * radius + 1, 0.0));
    float sigma = amount / 3.0;
    float sum = 0.0;

    for (int i = -radius; i <= radius; ++i) {
        for (int j = -radius; j <= radius; ++j) {
            kernel[i + radius][j + radius] = exp(-(i * i + j * j) / (2.0 * sigma * sigma));
            sum += kernel[i + radius][j + radius];
        }
    }

    // Normalize the kernel
    for (int i = 0; i < 2 * radius + 1; ++i) {
        for (int j = 0; j < 2 * radius + 1; ++j) {
            kernel[i][j] /= sum;
        }
    }

    // Apply convolution with the Gaussian kernel
    int height = img.size();
    int width = img[0].size();

    vector<vector<Pixel>> result(img);

    for (int y = radius; y < height - radius; ++y) {
        for (int x = radius; x < width - radius; ++x) {
            float sumR = 0.0, sumG = 0.0, sumB = 0.0;

            for (int i = -radius; i <= radius; ++i) {
                for (int j = -radius; j <= radius; ++j) {
                    sumR += kernel[i + radius][j + radius] * img[y + i][x + j].r;
                    sumG += kernel[i + radius][j + radius] * img[y + i][x + j].g;
                    sumB += kernel[i + radius][j + radius] * img[y + i][x + j].b;
                }
            }

            result[y][x].r = max(min(static_cast<int>(sumR),255),0);
            result[y][x].g = max(min(static_cast<int>(sumG),255),0);
            result[y][x].b = max(min(static_cast<int>(sumB),255),0);
        }
    }

    // Copy the result back to the original image
    img = result;
}