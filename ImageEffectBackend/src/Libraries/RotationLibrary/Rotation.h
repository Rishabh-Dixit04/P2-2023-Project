#ifndef ROTATION_H
#define ROTATION_H
#include "../Pixel.h"

#include <vector>
using namespace std;

void rotateImage90(vector<vector<Pixel>>& image);
void rotateImage180(vector<vector<Pixel>>& image);
void rotateImage270(vector<vector<Pixel>>& image);
void rotate(vector<vector<Pixel>>& imageVector, int value);

#endif
