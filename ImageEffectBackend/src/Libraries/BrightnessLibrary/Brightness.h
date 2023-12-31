#ifndef BRIGHTNESS_H
#define BRIGHTNESS_H

#include "../Pixel.h"
#include <vector>

using namespace std;

void applyBrightness(vector<vector<Pixel> > &img, float amount);

#endif