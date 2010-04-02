from django.db import models
from django.contrib.auth.models import User

class Position(models.Model):
    TYPE_CHOICES = (
            ('V', 'Volunteer'), # Pay may be 0
            ('H', 'Hourly'),    # Pay is per hour
            ('S', 'Salary'),    # Pay is per year
            ('C', 'Contract'),  # Pay is one-time
            )

    name = models.CharField(max_length = 250)
    short_description = models.CharField(max_length = 500)
    long_description = models.TextField(blank = True)
    type = models.CharField(max_length = 1, choices = TYPE_CHOICES)
    pay = models.DecimalField(max_digits = 8, decimal_places = 2, default = 0.00)

class Employee(models.Model):
    user = models.OneToOneField(User)
    position = models.ForeignKey('Position')
    phone = models.CharField(max_length = 20)
    address1 = models.CharField(max_length = 250)
    address2 = models.CharField(max_length = 250, blank = True)
    city = models.CharField(max_length = 100)
    state = models.CharField(max_length = 100)
    zipcode = models.CharField(max_length = 10)
    country = models.CharField(max_length = 100, blank = True, default = "United States of America")

class TimeClockEvent(models.Model):
    user = models.ForeignKey('Employee')
    ctime = models.DateTimeField(auto_now_add = True)
    clockin = models.BooleanField(default = False)

class Customer(models.Model):
    user = models.OneToOneField(User)
    organization = models.CharField(max_length = 250, blank = True)
    phone = models.CharField(max_length = 20, blank = True)
    address1 = models.CharField(max_length = 250, blank = True)
    address2 = models.CharField(max_length = 250, blank = True)
    city = models.CharField(max_length = 100, blank = True)
    state = models.CharField(max_length = 100, blank = True)
    zipcode = models.CharField(max_length = 10, blank = True)
    country = models.CharField(max_length = 100, blank = True, default = "United States of America")

class Order(models.Model):
    STATUS_CHOICES = (
            ('0', 'Finished Shopping'),
            ('1', 'Still Shopping'),
            ('2', 'System Error'),
            ('3', 'User reported problem'),
            ('4', 'Offline order'),
            )

    owner = models.ForeignKey('Customer')
    name = models.CharField(max_length = 125)
    is_active = models.OneToOneField('Customer', null = True, blank = True, related_name = 'active_order')
    is_shared = models.BooleanField(default = False)
    ctime = models.DateTimeField(auto_now_add = True, blank = False)
    mtime = models.DateTimeField(auto_now = True, blank = False)
    local_status = models.CharField(max_length = 1, choices = STATUS_CHOICES)
    state = models.CharField(max_length = 16, blank = True)
    payment = models.CharField(max_length = 16, blank = True)
    google_id = models.CharField(max_length = 255, blank = True)
    cart_xml = models.TextField(blank = True)
    notes = models.TextField(blank = True)

    def get_total(self):
        price = 0
        for item in self.item_set.all():
            price += item.product.price * item.quantity
        return price

class TroubleTicket(models.Model):
    STATUS_CHOICES = (
            ('U', 'Unresolved'),
            ('A', 'Assigned'),
            ('P', 'Partially Resolved'),
            ('R', 'Resolved'),
            )

    PRIORITY_CHOICES = (
            ('L', 'Low'),
            ('N', 'Normal'),
            ('H', 'High'),
            ('U', 'URGENT'),
            )

    order = models.ForeignKey('Order')
    owner = models.ForeignKey('Employee')
    problem = models.TextField()
    solution = models.TextField(blank = True)
    status = models.CharField(max_length = 1, choices = STATUS_CHOICES, default = 'U')
    priority = models.CharField(max_length = 1, choices = PRIORITY_CHOICES, default = 'N')

class Item(models.Model):
    order = models.ForeignKey('Order')
    product = models.ForeignKey('Product')
    quantity = models.IntegerField(default = 0)

class Transaction(models.Model):
    TRANSACTION_TYPES = (
            ('S', 'Sale'),             # Product sale
            ('P', 'Employee payment'), # Employee payment
            ('Y', 'Creator payment'),  # Payment to creator
            ('C', 'Credit'),           # Store credit
            ('E', 'Expense'),          # Expense with no assets gained
            ('A', 'Asset purchase'),   # Expense with assets gained
            ('R', 'Product purchase'), # Purchase of product with intent to sell
            )

    type = models.CharField(max_length = 1, choices = TRANSACTION_TYPES)
    amount = models.DecimalField(max_digits = 8, decimal_places = 2)
    order = models.ForeignKey('Order', blank = True, null = True)
    description = models.TextField(blank = True)

class Creator(models.Model):
    user = models.OneToOneField(User)
    profile = models.TextField()
    phone = models.CharField(max_length = 20)
    address1 = models.CharField(max_length = 250)
    address2 = models.CharField(max_length = 250, blank = True)
    city = models.CharField(max_length = 100)
    state = models.CharField(max_length = 100)
    zipcode = models.CharField(max_length = 10)
    country = models.CharField(max_length = 100, blank = True, default = "United States of America")
    

class Product(models.Model):
    STATUS_CHOICES = (
            ('0', 'Available'),
            ('1', 'Coming Soon'),
            ('2', 'Out of stock'),
            ('3', 'Unavailable'),
            ('4', 'Hidden')
            )

    sku = models.SlugField(primary_key = True)
    status = models.CharField(max_length = 1, choices = STATUS_CHOICES)
    quantity = models.IntegerField(default = 0)
    name = models.CharField(max_length = 250)
    short_desc = models.CharField(max_length = 500, blank = True)
    long_desc = models.TextField(blank = True)
    creator = models.ForeignKey('Creator', blank = True, null = True)
    width = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    length = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    height = models.DecimalField(max_digits = 3, decimal_places = 1, null = True)
    price = models.DecimalField(max_digits = 8, decimal_places = 2)
