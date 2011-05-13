#!/usr/bin/env jruby
require 'buildr'

# Specify Maven 2.0 remote repositories here:
repositories.remote << 'http://www.ibiblio.org/maven2/'

# Specify the project's directory layout here:
LAYOUT = Layout.new
LAYOUT[:source, :main, :java]       = 'src'
LAYOUT[:source, :spec, :ruby]       = 'spec'
LAYOUT[:reports, :rspec]            = 'spec/reports'
LAYOUT[:target, :main]              = 'pkg'
LAYOUT[:target]                     = 'pkg'

desc   "Dydra SDK for Java"
define 'dydra', :layout => LAYOUT do
  project.version = File.read('VERSION').chomp
  project.group   = 'dydra'
  manifest['Implementation-Vendor']  = 'Dydra.com'
  manifest['Implementation-Version'] = project.version

  compile.options.target = '1.5'
  test.using :rspec
  package :jar
end

RakeFileUtils.verbose_flag = false
