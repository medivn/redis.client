FROM ubuntu14.04:update
MAINTAINER Matt Baldwin (baldwin@stackpointcloud.com)


# 更新源
RUN \
   apt-get update && apt-get -y --no-install-recommends install \
    ca-certificates \
    software-properties-common \
    python-django-tagging \
    python-simplejson \
    python-memcache \
    python-ldap \
    python-cairo \
    python-pysqlite2 \
    python-support \
    python-pip \
    gunicorn \
    supervisor \
    nginx-light \
    nodejs \
    git \
    curl \
    openjdk-7-jre \
    build-essential \
    python-dev


WORKDIR /opt
COPY influxdb_1.1.1_amd64.deb /opt/influxdb_1.1.1_amd64.deb
COPY grafana-latest.linux-x64.tar.gz /opt/grafana-latest.linux-x64.tar.gz
RUN \
  mkdir grafana
RUN \
  tar -xzf grafana-latest.linux-x64.tar.gz --directory grafana --strip-components=1 
RUN \
 dpkg -i influxdb_1.1.1_amd64.deb && \
  echo "influxdb soft nofile unlimited" >> /etc/security/limits.conf && \
  echo "influxdb hard nofile unlimited" >> /etc/security/limits.conf

ADD config.js /opt/grafana/config.js
ADD nginx.conf /etc/nginx/nginx.conf
ADD supervisord.conf /etc/supervisor/conf.d/supervisord.conf
ADD config.toml /opt/influxdb/current/config.toml

VOLUME ["/opt/influxdb/shared/data"]

EXPOSE 80 8083 8086 2003

CMD ["supervisord", "-n"]
