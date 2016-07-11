# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "ubuntu"
  config.vm.box_url = "https://cloud-images.ubuntu.com/vagrant/precise/current/precise-server-cloudimg-amd64-vagrant-disk1.box"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  config.vm.network "public_network", bridge: "wlan0", ip: "192.168.88.5"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  # config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
  #   vb.memory = "1024"
  # end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Define a Vagrant Push strategy for pushing to Atlas. Other push strategies
  # such as FTP and Heroku are also available. See the documentation at
  # https://docs.vagrantup.com/v2/push/atlas.html for more information.
  # config.push.define "atlas" do |push|
  #   push.app = "YOUR_ATLAS_USERNAME/YOUR_APPLICATION_NAME"
  # end

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
   config.vm.provision "shell", inline: <<-SHELL
     sudo apt-get update

     debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
     debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'

     sudo apt-get install -y mysql-server
     sudo apt-get install -y freeradius
     sudo apt-get install -y libfreeradius2
     sudo apt-get install -y freeradius-common
     sudo apt-get install -y freeradius-mysql
     sudo apt-get install -y freeradius-utils

cat > /etc/mysql/my.cnf << EOF
[client]
port		= 3306
socket		= /var/run/mysqld/mysqld.sock
[mysqld_safe]
socket		= /var/run/mysqld/mysqld.sock
nice		= 0
[mysqld]
user		= mysql
pid-file	= /var/run/mysqld/mysqld.pid
socket		= /var/run/mysqld/mysqld.sock
port		= 3306
basedir		= /usr
datadir		= /var/lib/mysql
tmpdir		= /tmp
lc-messages-dir	= /usr/share/mysql
skip-external-locking
bind-address		= 0.0.0.0
key_buffer		= 16M
max_allowed_packet	= 16M
thread_stack		= 192K
thread_cache_size       = 8
myisam-recover         = BACKUP
query_cache_limit	= 1M
query_cache_size        = 16M
log_error = /var/log/mysql/error.log
expire_logs_days	= 10
max_binlog_size         = 100M
[mysqldump]
quick
quote-names
max_allowed_packet	= 16M
[mysql]
[isamchk]
key_buffer		= 16M
!includedir /etc/mysql/conf.d/
EOF

     sudo /etc/init.d/mysql restart
     echo "create database radius;" | mysql -uroot -proot
     echo "create user 'root'@'%' identified by 'root';" | mysql -uroot -proot
     echo "grant all privileges on *.* to 'root'@'%';" | mysql -uroot -proot
     echo "flush privileges;" | mysql -uroot -proot
     mysql -uroot -proot -D radius < /etc/freeradius/sql/mysql/schema.sql
     mysql -uroot -proot -D radius < /etc/freeradius/sql/mysql/nas.sql

     cat > /etc/freeradius/sql.conf << EOF
sql {
  database = "mysql"
  driver = "rlm_sql_\\${database}"
  server = "localhost"
  login = "root"
  password = "root"
  radius_db = "radius"
  acct_table1 = "radacct"
  acct_table2 = "radacct"
  postauth_table = "radpostauth"
  authcheck_table = "radcheck"
  authreply_table = "radreply"
  groupcheck_table = "radgroupcheck"
  groupreply_table = "radgroupreply"
  usergroup_table = "radusergroup"
  deletestalesessions = yes
  sqltrace = no
  sqltracefile = \\${logdir}/sqltrace.sql
  num_sql_socks = 5
  connect_failure_retry_delay = 60
  lifetime = 0
  max_queries = 0
  nas_table = "nas"
  \\$INCLUDE sql/\\${database}/dialup.conf
}
EOF

     cat > /etc/freeradius/sites-enabled/default << EOF
authorize {
  preprocess
  chap
  mschap
  digest
  suffix
  eap {
    ok = return
  }
  files
  sql
  expiration
  logintime
  pap
}
authenticate {
  Auth-Type PAP {
    pap
  }
  Auth-Type CHAP {
    chap
  }
  Auth-Type MS-CHAP {
    mschap
  }
  digest
  unix
  eap
}
preacct {
  preprocess
  acct_unique
  suffix
  files
}
accounting {
  detail
  sql
  exec
  attr_filter.accounting_response
}
session {
  radutmp
}
post-auth {
  exec
  Post-Auth-Type REJECT {
    attr_filter.access_reject
  }
}
pre-proxy {
}
post-proxy {
  eap
}
EOF

      cat > /etc/freeradius/radiusd.conf << EOF
prefix = /usr
exec_prefix = /usr
sysconfdir = /etc
localstatedir = /var
sbindir = \\${exec_prefix}/sbin
logdir = /var/log/freeradius
raddbdir = /etc/freeradius
radacctdir = \\${logdir}/radacct
name = freeradius
confdir = \\${raddbdir}
run_dir = \\${localstatedir}/run/${name}
db_dir = \\${raddbdir}
libdir = /usr/lib/freeradius
pidfile = \\${run_dir}/${name}.pid
user = freerad
group = freerad
max_request_time = 30
cleanup_delay = 5
max_requests = 1024
listen {
  type = auth
  ipaddr = *
  port = 0
}
listen {
  ipaddr = *
  port = 0
  type = acct
}
hostname_lookups = no
allow_core_dumps = no
regular_expressions = yes
extended_expressions  = yes
log {
  destination = files
  file = \\${logdir}/radius.log
  syslog_facility = daemon
  stripped_names = no
  auth = no
  auth_badpass = no
  auth_goodpass = no
}
checkrad = \\${sbindir}/checkrad
security {
  max_attributes = 200
  reject_delay = 1
  status_server = yes
  allow_vulnerable_openssl = no
}
proxy_requests  = yes
\\$INCLUDE proxy.conf
\\$INCLUDE clients.conf
thread pool {
  start_servers = 5
  max_servers = 32
  min_spare_servers = 3
  max_spare_servers = 10
  max_requests_per_server = 0
}
modules {
  \\$INCLUDE \\${confdir}/modules/
  \\$INCLUDE eap.conf
  \\$INCLUDE sql.conf
}
instantiate {
  exec
  expr
  expiration
  logintime
}
\\$INCLUDE policy.conf
\\$INCLUDE sites-enabled/
EOF
       echo "\\$INCLUDE		/usr/share/freeradius/dictionary.mikrotik" >> /etc/freeradius/dictionary

       echo "client 192.168.88.0/24 {" >> /etc/freeradius/clients.conf
       echo "\tsecret\t\t= testing123" >> /etc/freeradius/clients.conf
       echo "\tshortname\t= mikrotik" >> /etc/freeradius/clients.conf
       echo "}" >> /etc/freeradius/clients.conf

       sudo /etc/init.d/freeradius stop
       sudo freeradius -X &
   SHELL
end
