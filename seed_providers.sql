-- ============================================================
-- ServiceMate — Seed Data for All Service Providers
-- Run this in MySQL Workbench or: mysql -u root -p servicemate_db < seed_providers.sql
-- ============================================================

USE servicemate_db;

-- Clear existing providers (optional — comment out if you want to keep existing)
-- DELETE FROM bookings;
-- DELETE FROM service_providers;

INSERT INTO service_providers
  (name, phone, category, location, experience, price_per_hour, rating, review_count, description, available)
VALUES

-- ── AC Repair ────────────────────────────────────────────────────────────────
('Rajesh Kumar',    '9876543201', 'ac-repair',          'Koramangala',   '8 years',  499.0, 4.8, 312, 'Certified AC technician specialising in split and window ACs. Fast diagnosis and genuine spare parts.', true),
('Suresh Patil',    '9876543202', 'ac-repair',          'Indiranagar',   '5 years',  399.0, 4.6, 198, 'Expert in Voltas, Daikin and LG AC servicing. Same-day service available.', true),
('Anand Verma',     '9876543203', 'ac-repair',          'Whitefield',    '10 years', 549.0, 4.9, 421, 'Senior AC technician with a decade of experience. Specialises in commercial and residential units.', true),

-- ── Electrician ──────────────────────────────────────────────────────────────
('Vikram Singh',    '9876543204', 'electrician',        'BTM Layout',    '6 years',  349.0, 4.7, 267, 'Licensed electrician handling wiring, panel upgrades, and appliance installation safely.', true),
('Mohan Reddy',     '9876543205', 'electrician',        'HSR Layout',    '4 years',  299.0, 4.5, 143, 'Handles all electrical repairs including short circuits, fan fitting, and switchboard work.', true),
('Ravi Shankar',    '9876543206', 'electrician',        'Marathahalli',  '9 years',  449.0, 4.8, 389, 'Industrial and domestic electrician. Available for urgent callouts within 30 minutes.', true),

-- ── Plumber ──────────────────────────────────────────────────────────────────
('Deepak Nair',     '9876543207', 'plumber',            'JP Nagar',      '7 years',  349.0, 4.7, 234, 'Handles pipe leaks, tap fitting, drainage blockage, and water heater installation.', true),
('Arun Kumar',      '9876543208', 'plumber',            'Electronic City','5 years', 299.0, 4.5, 176, 'Expert in bathroom fittings, concealed piping, and overhead tank connections.', true),
('Sanjay Gupta',    '9876543209', 'plumber',            'Jayanagar',     '12 years', 499.0, 4.9, 512, 'Master plumber with 12 years of experience. Specialises in complete bathroom renovation plumbing.', true),

-- ── Washing Machine ──────────────────────────────────────────────────────────
('Pradeep Rao',     '9876543210', 'washing-machine',    'Malleshwaram',  '5 years',  349.0, 4.6, 189, 'Expert in front-load and top-load washing machine repair. All brands serviced.', true),
('Kartik Sharma',   '9876543211', 'washing-machine',    'Rajajinagar',   '3 years',  299.0, 4.4, 112, 'Handles drum issues, motor problems, and electronic control board repairs.', true),
('Harish Babu',     '9876543212', 'washing-machine',    'Banashankari',  '8 years',  399.0, 4.8, 298, 'Samsung, LG, Whirlpool certified service engineer. Genuine spare parts used.', true),

-- ── Refrigerator Repair ──────────────────────────────────────────────────────
('Naveen Gowda',    '9876543213', 'refrigerator',       'Hebbal',        '6 years',  349.0, 4.6, 201, 'Handles gas charging, compressor replacement, thermostat issues for all fridge brands.', true),
('Sunil Joshi',     '9876543214', 'refrigerator',       'Yelahanka',     '4 years',  299.0, 4.4, 134, 'Expert in double-door and single-door refrigerator repairs. Quick turnaround.', true),
('Amit Kulkarni',   '9876543215', 'refrigerator',       'Bommanahalli',  '9 years',  449.0, 4.8, 367, 'Senior fridge technician. Specialises in frost-free and inverter compressor models.', true),

-- ── TV Installation ──────────────────────────────────────────────────────────
('Girish Patel',    '9876543216', 'tv-installation',    'Koramangala',   '4 years',  249.0, 4.5, 156, 'LED, OLED, and QLED TV wall mounting and installation expert. Clean cable management.', true),
('Rahul Hegde',     '9876543217', 'tv-installation',    'Indiranagar',   '3 years',  199.0, 4.3, 98,  'Mounts all TV sizes. Also handles soundbar, set-top box, and home theatre setup.', true),
('Kiran Bhat',      '9876543218', 'tv-installation',    'Sarjapur Road', '6 years',  299.0, 4.7, 213, 'Professional TV mounting with proper wall studs and concealed wiring. Same-day booking available.', true),

-- ── Deep Cleaning ────────────────────────────────────────────────────────────
('Lakshmi Devi',    '9876543219', 'deep-cleaning',      'Whitefield',    '5 years',  599.0, 4.8, 342, 'Full home deep cleaning using eco-friendly products. Specialises in move-in/move-out cleaning.', true),
('Meena Kumari',    '9876543220', 'deep-cleaning',      'HSR Layout',    '3 years',  499.0, 4.6, 187, 'Thorough kitchen, bathroom, and bedroom cleaning. Team of 2 professionals.', true),
('Priya Naidu',     '9876543221', 'deep-cleaning',      'Marathahalli',  '7 years',  699.0, 4.9, 456, 'Premium deep cleaning service. All surfaces, appliances, and fixtures cleaned to perfection.', true),

-- ── Bathroom Cleaning ────────────────────────────────────────────────────────
('Savitha Reddy',   '9876543222', 'bathroom-cleaning',  'Jayanagar',     '4 years',  349.0, 4.7, 223, 'Expert bathroom deep cleaning — tiles, grout, fixtures, and sanitization. Odour-free guarantee.', true),
('Rekha Pillai',    '9876543223', 'bathroom-cleaning',  'BTM Layout',    '3 years',  299.0, 4.5, 145, 'Thorough bathroom scrubbing and disinfection. Uses professional-grade descalers and sanitizers.', true),
('Anitha Shetty',   '9876543224', 'bathroom-cleaning',  'JP Nagar',      '6 years',  399.0, 4.8, 312, 'Bathroom renovation-level cleaning. Removes stubborn stains, limescale, and mold effectively.', true),

-- ── Sofa Cleaning ────────────────────────────────────────────────────────────
('Prasad Rao',      '9876543225', 'sofa-cleaning',      'Koramangala',   '5 years',  449.0, 4.7, 198, 'Dry and wet sofa cleaning for fabric, leather, and suede. Stain removal specialist.', true),
('Kishore Kumar',   '9876543226', 'sofa-cleaning',      'Indiranagar',   '3 years',  349.0, 4.5, 132, 'Uses steam cleaning technology. Removes allergens, pet hair, and deep-set stains.', true),
('Venkat Raman',    '9876543227', 'sofa-cleaning',      'Whitefield',    '8 years',  549.0, 4.8, 287, 'Premium sofa restoration service. Works on all materials including velvet and microfiber.', true),

-- ── Carpet Cleaning ──────────────────────────────────────────────────────────
('Suresh Iyer',     '9876543228', 'carpet-cleaning',    'Banashankari',  '6 years',  399.0, 4.6, 176, 'Hot water extraction carpet cleaning. Removes dust mites, stains, and bad odour effectively.', true),
('Ramu Krishnan',   '9876543229', 'carpet-cleaning',    'Malleshwaram',  '4 years',  349.0, 4.4, 123, 'Handles Persian rugs, woolen carpets, and synthetic mats. Fast drying process.', true),
('Balaji Naicker',  '9876543230', 'carpet-cleaning',    'Rajajinagar',   '9 years',  499.0, 4.9, 334, 'Professional carpet washing and steam cleaning. Certified by IICRC for textile care.', true),

-- ── Kitchen Cleaning ─────────────────────────────────────────────────────────
('Usha Rani',       '9876543231', 'kitchen-cleaning',   'Electronic City','4 years', 399.0, 4.7, 234, 'Deep kitchen cleaning — degreasing chimney, stove, tiles, and cabinets. Hygienic and thorough.', true),
('Kavitha Reddy',   '9876543232', 'kitchen-cleaning',   'Hebbal',         '3 years', 349.0, 4.5, 156, 'Expert in modular kitchen cleaning. Reaches all corners and removes baked-on grease.', true),
('Suma Nagaraj',    '9876543233', 'kitchen-cleaning',   'Yelahanka',      '7 years', 499.0, 4.8, 301, 'Commercial-grade cleaning solutions for kitchens. Leaves surfaces spotless and sanitized.', true),

-- ── Haircut ──────────────────────────────────────────────────────────────────
('Arjun Stylist',   '9876543234', 'haircut',            'Koramangala',   '6 years',  299.0, 4.8, 412, 'Trending haircuts, fades, and beard styling at your home. Trained at a premium salon.', true),
('Akash Barber',    '9876543235', 'haircut',            'Indiranagar',   '4 years',  249.0, 4.6, 267, 'Clean cuts and classic styles. Brings professional tools and sanitized equipment.', true),
('Nikhil Salon',    '9876543236', 'haircut',            'HSR Layout',    '8 years',  349.0, 4.9, 521, 'Senior stylist with experience in international hair trends. Specialises in curly and textured hair.', true),

-- ── Makeup Artist ────────────────────────────────────────────────────────────
('Divya Sharma',    '9876543237', 'makeup',             'Jayanagar',     '5 years',  799.0, 4.9, 389, 'Bridal and party makeup specialist. Uses branded, skin-safe products. Airbrush available.', true),
('Pooja Menon',     '9876543238', 'makeup',             'BTM Layout',    '3 years',  599.0, 4.7, 234, 'Expert in natural and glam looks. Available for weddings, receptions, and photoshoots.', true),
('Sneha Pillai',    '9876543239', 'makeup',             'Marathahalli',  '7 years',  999.0, 4.9, 567, 'Certified MUA with 7 years of bridal experience. HD and airbrush makeup specialist.', true),

-- ── Spa / Massage ────────────────────────────────────────────────────────────
('Ananya Patel',    '9876543240', 'spa',                'Whitefield',    '5 years',  699.0, 4.8, 298, 'Swedish, deep tissue, and aromatherapy massage at your home. Certified therapist.', true),
('Ritu Kapoor',     '9876543241', 'spa',                'Sarjapur Road', '4 years',  599.0, 4.6, 212, 'Relaxing full-body massages and foot reflexology. Brings oils and professional massage table.', true),
('Swati Jain',      '9876543242', 'spa',                'Koramangala',   '8 years',  899.0, 4.9, 445, 'Luxury spa experience at home. Thai, Balinese, and hot stone massage specialist.', true),

-- ── Fitness Trainer ──────────────────────────────────────────────────────────
('Rohit Fitness',   '9876543243', 'fitness',            'Indiranagar',   '5 years',  499.0, 4.8, 312, 'Certified personal trainer (CPT). Weight loss, muscle gain, and flexibility programs.', true),
('Vivek Coach',     '9876543244', 'fitness',            'HSR Layout',    '3 years',  399.0, 4.6, 187, 'Home workout specialist. Creates personalised plans without gym equipment.', true),
('Manoj Trainer',   '9876543245', 'fitness',            'Koramangala',   '8 years',  599.0, 4.9, 423, 'Elite personal trainer. Athlete background with expertise in strength and conditioning.', true),

-- ── Bike Servicing ───────────────────────────────────────────────────────────
('Raju Mechanic',   '9876543246', 'bike-servicing',     'JP Nagar',      '7 years',  299.0, 4.7, 245, 'Complete two-wheeler servicing at your doorstep. All brands — Honda, Hero, Bajaj, Royal Enfield.', true),
('Shiva Bikes',     '9876543247', 'bike-servicing',     'Electronic City','5 years', 249.0, 4.5, 178, 'Engine tune-up, oil change, brake pad replacement, and chain lubrication.', true),
('Ganesh Motors',   '9876543248', 'bike-servicing',     'Bommanahalli',  '10 years', 349.0, 4.8, 389, 'Master mechanic for bikes and scooters. Includes washing and tyre pressure check.', true),

-- ── Car Washing ──────────────────────────────────────────────────────────────
('Speed Car Wash',  '9876543249', 'car-wash',           'Marathahalli',  '4 years',  349.0, 4.7, 312, 'Premium doorstep car wash — exterior, interior vacuum, and dashboard wipe. Foam wash technique.', true),
('Shine Auto',      '9876543250', 'car-wash',           'Whitefield',    '3 years',  299.0, 4.5, 198, 'Waterless and foam car wash options. Eco-friendly products. Quick 45-minute service.', true),
('Clean Wheels',    '9876543251', 'car-wash',           'Sarjapur Road', '6 years',  449.0, 4.8, 267, 'Full detailing service — exterior polish, tyre dressing, and interior deep clean.', true),

-- ── Car Repair ───────────────────────────────────────────────────────────────
('Auto Expert',     '9876543252', 'car-repair',         'Koramangala',   '8 years',  599.0, 4.7, 289, 'Multi-brand car mechanic. Handles engine, suspension, AC, and electrical issues.', true),
('Fix My Car',      '9876543253', 'car-repair',         'Indiranagar',   '6 years',  499.0, 4.5, 213, 'Doorstep car repair for minor to moderate issues. Denting, painting, and oil service.', true),
('QuickFix Autos',  '9876543254', 'car-repair',         'HSR Layout',    '10 years', 699.0, 4.9, 412, 'Certified multi-brand technician. Computerised diagnostics available on-site.', true),

-- ── Battery Replacement ───────────────────────────────────────────────────────
('BatteryMan',      '9876543255', 'battery-replacement','BTM Layout',    '5 years',  299.0, 4.7, 198, 'Doorstep car and bike battery replacement. Exide, Amaron, and Luminous batteries in stock.', true),
('PowerUp Service', '9876543256', 'battery-replacement','Malleshwaram',  '3 years',  249.0, 4.5, 145, 'Fast battery jump-start and replacement. Warranty on all batteries fitted.', true),
('Volt Master',     '9876543257', 'battery-replacement','Banashankari',  '7 years',  349.0, 4.8, 267, 'All vehicle battery brands available. Old battery disposal included in service.', true),

-- ── Home Painting ────────────────────────────────────────────────────────────
('Colour Expert',   '9876543258', 'painting',           'Whitefield',    '8 years',  499.0, 4.7, 312, 'Interior and exterior house painting. Asian Paints, Berger, and Nerolac certified applicator.', true),
('Brushstroke Pro', '9876543259', 'painting',           'Hebbal',        '5 years',  399.0, 4.5, 198, 'Clean and precise painting work. Waterproofing, texture, and enamel paint specialist.', true),
('Paint Perfect',   '9876543260', 'painting',           'Yelahanka',     '12 years', 599.0, 4.9, 456, 'Master painter with 12 years experience. Handles everything from single rooms to entire buildings.', true),

-- ── Pest Control ─────────────────────────────────────────────────────────────
('PestAway',        '9876543261', 'pest-control',       'Jayanagar',     '6 years',  599.0, 4.8, 289, 'Cockroach, rodent, termite, and mosquito treatment. Eco-friendly chemicals with 90-day guarantee.', true),
('BugBusters',      '9876543262', 'pest-control',       'JP Nagar',      '4 years',  499.0, 4.6, 212, 'Residential and commercial pest control. Gel treatment for cockroaches, no smell.', true),
('ZeroPest',        '9876543263', 'pest-control',       'Koramangala',   '9 years',  699.0, 4.9, 378, 'Licensed pest control operator. Comprehensive treatment for all pests with chemical-free options.', true),

-- ── Carpenter ────────────────────────────────────────────────────────────────
('Raman Carpenter', '9876543264', 'carpenter',          'BTM Layout',    '10 years', 399.0, 4.7, 312, 'Custom furniture, door repair, wardrobe fixing, and shelving. All wood types handled.', true),
('Wood Works',      '9876543265', 'carpenter',          'Electronic City','7 years', 349.0, 4.5, 234, 'Expert in modular furniture assembly, bed frames, and cabinet repairs.', true),
('Master Joinery',  '9876543266', 'carpenter',          'Marathahalli',  '14 years', 499.0, 4.9, 489, 'Senior carpenter with expertise in teak, plywood, and MDF work. Custom designs available.', true),

-- ── Interior Decoration ──────────────────────────────────────────────────────
('Design Studio',   '9876543267', 'interior',           'Indiranagar',   '6 years',  999.0, 4.8, 267, 'Home interior styling — curtains, wall art, furniture arrangement, and colour consultation.', true),
('Decor Dreams',    '9876543268', 'interior',           'Koramangala',   '4 years',  799.0, 4.6, 198, 'Affordable interior touches — lighting, cushions, plants, and decorative pieces.', true),
('Elegant Spaces',  '9876543269', 'interior',           'Whitefield',    '9 years', 1299.0, 4.9, 412, 'Award-winning interior designer. Complete room makeovers with 3D visualisation preview.', true),

-- ── Emergency Electrician ────────────────────────────────────────────────────
('24H Electrician', '9876543270', 'emergency-electrician','HSR Layout',  '8 years',  599.0, 4.8, 345, 'Available 24/7 for electrical emergencies — short circuits, tripped MCBs, power outages.', true),
('Flash Electric',  '9876543271', 'emergency-electrician','BTM Layout',  '5 years',  499.0, 4.6, 223, 'Rapid response electrician. Arrives within 30 minutes. All emergency electrical issues fixed.', true),
('SOS Electric',    '9876543272', 'emergency-electrician','Koramangala', '10 years', 699.0, 4.9, 456, 'Certified emergency electrician. Handles dangerous situations safely with full safety gear.', true),

-- ── Emergency Plumber ────────────────────────────────────────────────────────
('Rapid Plumber',   '9876543273', 'emergency-plumber',  'Jayanagar',     '7 years',  599.0, 4.8, 312, 'Available round the clock for burst pipes, severe leaks, and drain blockages. 30-min response.', true),
('24H Plumbing',    '9876543274', 'emergency-plumber',  'JP Nagar',      '5 years',  499.0, 4.6, 198, 'Emergency plumbing solutions. Stops leaks fast and prevents water damage.', true),
('AquaFix',         '9876543275', 'emergency-plumber',  'Indiranagar',   '9 years',  699.0, 4.9, 389, 'Senior plumber for critical situations. Carries full toolkit and spare fittings at all times.', true),

-- ── Locksmith ────────────────────────────────────────────────────────────────
('Key Master',      '9876543276', 'locksmith',          'Marathahalli',  '6 years',  349.0, 4.7, 267, 'Lock installation, key duplication, and lockout assistance. All lock brands handled.', true),
('OpenSesame',      '9876543277', 'locksmith',          'Whitefield',    '4 years',  299.0, 4.5, 178, 'Fast lockout service. Door locks, digital locks, and padlock specialists.', true),
('SecureKey',       '9876543278', 'locksmith',          'Sarjapur Road', '9 years',  449.0, 4.9, 345, 'Master locksmith handling home security upgrades, smart locks, and deadbolts.', true);
