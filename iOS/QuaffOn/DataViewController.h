//
//  DataViewController.h
//  QuaffOn
//
//  Created by Corbitt O'Connor on 1/21/15.
//  Copyright (c) 2015 Corbitt O'Connor. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DataViewController : UITableViewController
@property (strong, nonatomic) IBOutlet UITextField *NotificationText;
@property (strong, nonatomic) IBOutlet UITextField *SexText;
@property (strong, nonatomic) IBOutlet UIDatePicker *DatePick;
@property (strong, nonatomic) IBOutlet UITextField *EmailText;
@property (strong, nonatomic) IBOutlet UITextField *NameText;

@property (strong, nonatomic) IBOutlet UILabel *dataLabel;
@property (strong, nonatomic) id dataObject;

@end

