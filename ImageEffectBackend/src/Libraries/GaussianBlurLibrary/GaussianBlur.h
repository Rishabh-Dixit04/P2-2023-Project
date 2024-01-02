#ifndef GAUSSIAN_BLUR_H
#define GAUSSIAN_BLUR_H
#include "../Pixel.h"

#include <vector>
#include <cmath>

using namespace std;

void applyGaussianBlur(vector<vector<Pixel>> &img, float amount);

#endif