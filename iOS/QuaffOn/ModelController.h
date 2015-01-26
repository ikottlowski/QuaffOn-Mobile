//
//  ModelController.h
//  QuaffOn
//
//  Created by Corbitt O'Connor on 1/21/15.
//  Copyright (c) 2015 Corbitt O'Connor. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DataViewController;

@interface ModelController : NSObject <UIPageViewControllerDataSource>

- (DataViewController *)viewControllerAtIndex:(NSUInteger)index storyboard:(UIStoryboard *)storyboard;
- (NSUInteger)indexOfViewController:(DataViewController *)viewController;

@end

