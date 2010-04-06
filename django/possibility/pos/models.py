"""
Models for the POS

@author Matthew Scott
@version $Id$
"""

from django.db import models
from django.contrib.auth.models import User
from django.contrib.sites.models import Site

class Address(models.Model):
    name = models.CharField(max_length = 200)
    organization = models.CharField(max_length = 200, blank = True)
    phone = models.CharField(max_length = 20)
    address1 = models.CharField(max_length = 250)
    address2 = models.CharField(max_length = 250, blank = True)
    city = models.CharField(max_length = 200)
    state = models.CharField(max_length = 100, blank = True)
    zipcode = models.CharField(max_length = 10, null = True)
    country = models.CharField(max_length = 100, blank = True, default = "United States of America")

class Position(models.Model):
    TYPE_CHOICES = (
            (0, 'Volunteer'), # Pay may be 0
            (1, 'Hourly'),    # Pay is per hour
            (2, 'Salary'),    # Pay is per year
            (3, 'Contract'),  # Pay is one-time
            )

    name = models.CharField(max_length = 250)
    short_description = models.CharField(max_length = 500)
    long_description = models.TextField(blank = True)
    type = models.IntegerField(choices = TYPE_CHOICES)
    pay = models.DecimalField(max_digits = 8, decimal_places = 2, default = 0.00)

class Employee(models.Model):
    user = models.OneToOneField(User)
    position = models.ForeignKey('Position')
    address = models.ForeignKey('Address')

class TimeClockEvent(models.Model):
    user = models.ForeignKey('Employee')
    ctime = models.DateTimeField(auto_now_add = True)
    clockin = models.BooleanField(default = False)

class Customer(models.Model):
    user = models.OneToOneField(User)
    billing_address = models.ForeignKey('Address', related_name = 'customer_billing_address')
    shipping_addresses = models.ManyToManyField('Address')

class Order(models.Model):
    STATUS_CHOICES = (
            (0, 'Finished Shopping'),
            (1, 'Still Shopping'),
            (2, 'System Error'),
            (3, 'User reported problem'),
            (4, 'Offline order'),
            )

    site = models.ForeignKey(Site)
    owner = models.ForeignKey('Customer')
    name = models.CharField(max_length = 125)
    is_active = models.OneToOneField('Customer', null = True, blank = True, related_name = 'active_order')
    is_shared = models.BooleanField(default = False)
    ctime = models.DateTimeField(auto_now_add = True, blank = False)
    mtime = models.DateTimeField(auto_now = True, blank = False)
    local_status = models.IntegerField(choices = STATUS_CHOICES)
    notes = models.TextField(blank = True)
    bill_to = models.ForeignKey('Address', related_name = 'order_billing_address')
    ship_to = models.ForeignKey('Address', related_name = 'order_shipping_address')
    is_gift = models.BooleanField(default = False)

    def get_total(self):
        price = 0
        for item in self.item_set.all():
            price += item.product.price * item.quantity
        return price

class TroubleTicket(models.Model):
    STATUS_CHOICES = (
            (0, 'Unresolved'),
            (1, 'Assigned'),
            (2, 'Partially Resolved'),
            (3, 'Resolved'),
            )

    PRIORITY_CHOICES = (
            (0, 'Low'),
            (1, 'Normal'),
            (2, 'High'),
            (3, 'URGENT'),
            )

    site = models.ForeignKey(Site, blank = True, null = True)
    order = models.ForeignKey('Order', blank = True, null = True)
    owner = models.ForeignKey('Employee', blank = True, null = True)
    problem = models.TextField()
    solution = models.TextField(blank = True)
    status = models.IntegerField(choices = STATUS_CHOICES, default = 0)
    priority = models.IntegerField(choices = PRIORITY_CHOICES, default = 1)

class Item(models.Model):
    order = models.ForeignKey('Order')
    product = models.ForeignKey('Product')
    quantity = models.IntegerField(default = 0)

class Transaction(models.Model):
    TRANSACTION_TYPES = (
            (0, 'Sale'),             # Product sale
            (1, 'Employee payment'), # Employee payment
            (3, 'Creator payment'),  # Payment to creator
            (4, 'Credit'),           # Store credit
            (5, 'Expense'),          # Expense with no assets gained
            (6, 'Asset purchase'),   # Expense with assets gained
            (7, 'Product purchase'), # Purchase of product with intent to sell
            )

    site = models.ForeignKey(Site)
    type = models.IntegerField(choices = TRANSACTION_TYPES)
    amount = models.DecimalField(max_digits = 8, decimal_places = 2)
    order = models.ForeignKey('Order', blank = True, null = True)
    description = models.TextField(blank = True)

class Creator(models.Model):
    user = models.OneToOneField(User)
    profile = models.TextField()
    address = models.ForeignKey('Address')

class Product(models.Model):
    STATUS_CHOICES = (
            (0, 'Available'),
            (1, 'Coming Soon'),
            (2, 'Out of stock'),
            (3, 'Unavailable'),
            (4, 'Hidden')
            )

    site = models.ForeignKey(Site)
    sku = models.SlugField(primary_key = True)
    status = models.IntegerField(choices = STATUS_CHOICES)
    quantity = models.IntegerField(default = 0)
    name = models.CharField(max_length = 250)
    short_desc = models.CharField(max_length = 500, blank = True)
    long_desc = models.TextField(blank = True)
    creator = models.ForeignKey('Creator', blank = True, null = True)
    width = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    length = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    height = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    price = models.DecimalField(max_digits = 8, decimal_places = 2)
